package euphoria.psycho.player

import android.graphics.Point
import android.media.*
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.text.InputType
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.GravityCompat
import euphoria.psycho.common.*
import euphoria.psycho.videos.R
import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.movie_view.*
import java.io.File
import java.io.FileFilter
import java.io.FileInputStream
import java.io.InputStream
import java.lang.Exception
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import kotlin.math.round

class VideoPlayer : AppCompatActivity(), SurfaceHolder.Callback, MediaPlayer.OnErrorListener,
    MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnTimedTextListener,
    TimeBar.OnScrubListener,
    MediaPlayer.OnSeekCompleteListener {


    private lateinit var mAudioManager: AudioManager
    private val mScreen = DisplayMetrics()
    private var mAudioMax = 0
    private var mCurrentPath: String? = null
    private var mCurrentState = STATE_IDLE
    private var mFov = 0f
    private var mInitTouchY = 0f
    private var mMediaPlayer: MediaPlayer? = null
    private var mSurfaceHeight = 0
    private var mSurfaceWidth = 0
    private var mSurfaceYDisplayRange = 0
    private var mTargetState = STATE_IDLE
    private var mTouchAction = TOUCH_NONE
    private var mTouchX = -1f
    private var mTouchY = -1f
    private var mVideoHeight = 0
    private var mVideoWidth = 0
    private var mVol = 0f
    private var mHasPaused = false
    private var mCurrentPosition = 0
    private var mDragging = false
    private val mStringBuilder = StringBuilder()
    private val mFormatter = Formatter(mStringBuilder, Locale.getDefault())
    private val mHandler = Handler()
    private var mRealHeight = 0


    private val mProgressBarChecker = Runnable {
        postProgressCheck()
    }


    private fun initialize() {
        mAudioManager = getAudioManager()
        mAudioMax = mAudioManager.getMusicVolume()
        // Log.e(TAG, "[initialize] Maximum volume of music stream is $mAudioMax")
        windowManager.defaultDisplay.getMetrics(mScreen)
        mRealHeight = mScreen.heightPixels - (getActionBarHeight() + getStatusBarHeight() + getSoftButtonsBarHeight())
        mSurfaceYDisplayRange = Math.min(mScreen.widthPixels, mScreen.heightPixels);
        intent.data?.let {
            mCurrentPath = it.path
        } ?: run {
            val mp4File = Environment.getExternalStorageDirectory().listFiles(FileFilter {
                it.isFile && it.name.endsWith(
                    ".mp4",
                    true
                )
            }).first()
            mCurrentPath = mp4File.absolutePath
        }
        setupToolBar(mCurrentPath?.getFilenameFromPath())
        setupSurfaceView()
        setupControls()
        start()
        time_bar.addListener(this)
    }
    private fun isInPlaybackState(): Boolean {
        return mMediaPlayer != null &&
                mCurrentState != STATE_ERROR &&
                mCurrentState != STATE_IDLE &&
                mCurrentState != STATE_PREPARING
    }
    override fun onCompletion(mp: MediaPlayer?) {
        mCurrentState = STATE_PLAYBACK_COMPLETED
        mTargetState = STATE_PLAYBACK_COMPLETED
        updateVisibility()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        initialize()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_player_options, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onDestroy() {
        stopPlayback()
        super.onDestroy()
    }
    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        mCurrentState = STATE_ERROR
        mTargetState = STATE_ERROR
        toast("what -> $what, extra-> $extra", false)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_jump -> openJumpDialog()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onPause() {
        mHasPaused = true
        mHandler.removeCallbacks(null)
        mCurrentPosition = mMediaPlayer?.currentPosition ?: 0
        release(false)
        super.onPause()
    }
    override fun onPrepared(mp: MediaPlayer) {
        mCurrentState = STATE_PREPARED
        mVideoWidth = mp.videoWidth
        mVideoHeight = mp.videoHeight
        setupTimeBar()
        if (mVideoHeight != 0 && mVideoWidth != 0) {
            setSurfaceViewSize()
            Log.e(TAG, "[onPrepared]  mSurfaceHeight $mSurfaceHeight mSurfaceWidth $mSurfaceWidth")
            surface_view.holder.setFixedSize(mVideoWidth, mVideoHeight)
            if (mSurfaceHeight == mVideoHeight && mSurfaceWidth == mVideoWidth) {
                if (mTargetState == STATE_PLAYING) {
                    start()
                }
            }
        } else {
            if (mTargetState == STATE_PLAYING)
                start()
        }
    }
    override fun onResume() {
        if (mHasPaused) {
            openVideo()
            start()
            seek(mCurrentPosition)
        }
        mHandler.post(mProgressBarChecker)
        super.onResume()
    }
    override fun onScrubMove(timeBar: TimeBar?, position: Long) {
        mMediaPlayer?.seekTo(position.toInt())
    }
    override fun onScrubStart(timeBar: TimeBar?, position: Long) {
        mDragging = true
    }
    override fun onScrubStop(timeBar: TimeBar?, position: Long, canceled: Boolean) {
        mDragging = false
        mMediaPlayer?.seekTo(position.toInt())
    }
    override fun onSeekComplete(mp: MediaPlayer) {
        Log.e(TAG, "[onSeekComplete] ${mp.currentPosition}")
        time_bar.setPosition(mp.duration.toLong())
    }
    override fun onTimedText(mp: MediaPlayer?, text: TimedText?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x_changed = if (mTouchX != -1f && mTouchY != -1f) event.rawX - mTouchX else 0f
        val y_changed = if (x_changed != 0f) event.rawY - mTouchY else 0f
        // coef is the gradient's move to determine a neutral zone
        val coef = Math.abs(y_changed / x_changed)
        val xgesturesize = ((x_changed / mScreen.xdpi) * 2.54f);
        val delta_y = Math.max(1f, (Math.abs(mInitTouchY - event.getRawY()) / mScreen.xdpi + 0.5f) * 2f);
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mTouchY = event.rawY
                mInitTouchY = event.rawY
                mVol = mAudioManager.getMusicVolume().toFloat()
                mTouchAction = TOUCH_NONE
                mTouchX = event.rawX
            }
            MotionEvent.ACTION_MOVE -> {
                if (mTouchAction != TOUCH_IGNORE) {
                    if (mFov == 0f) {
                        if (mTouchAction != TOUCH_SEEK && coef > 2) {
                            if (abs(y_changed / mSurfaceYDisplayRange) < 0.05) return false
                            mTouchY = event.rawY
                            mTouchX = event.rawX
                            //Log.e(TAG, "[onTouch] Sliding distance in the vertical direction: $y_changed")
                            touchVertical(y_changed)
                        } else {
                            touchSeek(round(delta_y).toInt(), xgesturesize, false)
                        }
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                if (mTouchAction == TOUCH_IGNORE) mTouchAction = TOUCH_NONE
                if (mTouchAction == TOUCH_SEEK) {
                    touchSeek(abs(delta_y).toInt(), xgesturesize, true)
                }
                mTouchY = -1f
                mTouchX = -1f
            }
        }
        return mTouchAction != TOUCH_NONE
    }
    private fun openJumpDialog() {
        val editText = EditText(this)
        editText.setText(getStringForTime(mStringBuilder, mFormatter, mMediaPlayer?.currentPosition.getLong()))
        AlertDialog.Builder(this)
            .setView(editText)
            .setMessage(
                getString(R.string.dialog_jump_description) + getStringForTime(
                    mStringBuilder,
                    mFormatter,
                    mMediaPlayer?.duration.getLong()
                )
            )
            .setPositiveButton("确定") { dialog, which ->
                Log.e(TAG, "${mMediaPlayer?.currentPosition} ${editText.text.toString().getTime()}")
                seek(editText.text.toString().getTime().toInt())
            }
            .setNegativeButton("取消") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }
    private fun openSubtitle() {
        val subtitle = mCurrentPath?.substringBeforeLast('.') + ".srt"
        if (subtitle.isFile()) {
            try {
                mMediaPlayer?.addTimedTextSource(subtitle, MIMETYPE_TEXT_SUBRIP)
            } catch (ignored: Exception) {
            }
        }
    }
    private fun openVideo() {
        if (mCurrentPath == null) return
        release(false)
        val that = this
        try {
            mMediaPlayer = MediaPlayer().apply {
                setOnPreparedListener(that)
                setOnCompletionListener(that)
                setOnErrorListener(that)
                setDataSource(mCurrentPath)
                setDisplay(surface_view.holder)
                setScreenOnWhilePlaying(true)
                prepareAsync()
                mCurrentState = STATE_PREPARING
            }
        } catch (ignored: Exception) {
            mCurrentState = STATE_ERROR
            mTargetState = STATE_ERROR
            Log.e(TAG, "[openVideo] ${ignored.message}")
        }
    }
    private fun pasue() {
        mMediaPlayer?.let {
            if (isInPlaybackState()) {
                if (it.isPlaying) {
                    it.pause()
                    mCurrentState = STATE_PAUSED
                }
            }
            mTargetState == STATE_PAUSED
        }
    }
    private fun postProgressCheck() {
        val pos = (mMediaPlayer?.currentPosition ?: 0).toLong()
        time_bar.setPosition(pos)
        //Log.e(TAG, getStringForTime(mStringBuilder, mFormatter, pos))
        text_position.text = getStringForTime(mStringBuilder, mFormatter, pos)
        mHandler.postDelayed(this.mProgressBarChecker, (1000 - (pos % 1000)).toLong())
    }
    private fun release(clearTargetState: Boolean) {
        mMediaPlayer?.apply {
            reset()
            release()
            mMediaPlayer = null
            mCurrentState = STATE_IDLE
            if (clearTargetState) {
                mTargetState = STATE_IDLE
            }
        }
    }
    private fun seek(time: Int) {
        mMediaPlayer?.seekTo(time)
    }
    private fun setAudioVolume(volume: Int) {
        val vol = volume
        if (vol <= mAudioMax) {
            if (vol != mAudioManager.getMusicVolume()) {
                try {
                    mAudioManager.setMusicVolume(vol)
                } catch (ignored: Exception) {
                }
            }
        }
    }
    private fun setSurfaceViewSize() {
        val width = mScreen.widthPixels
        val height = (mVideoHeight.toFloat() / mVideoWidth.toFloat() * mScreen.widthPixels.toFloat()).toInt()
        val lp = FrameLayout.LayoutParams(width, height)
        lp.gravity = Gravity.TOP
        Log.e(
            TAG,
            "[setSurfaceViewSize] $height  ${mScreen.widthPixels}x${mScreen.heightPixels} $mRealHeight ${(mRealHeight - height) / 2}"
        )
        lp.setMargins(0, (mRealHeight - height) / 2, 0, 0)
        surface_view.layoutParams = lp
//        val width = mScreen.widthPixels
//        val height = (mVideoHeight.toFloat() / mVideoWidth.toFloat() * mScreen.widthPixels.toFloat()).toInt()
//        val lp = ConstraintLayout.LayoutParams(width, height)
//        surface_view.layoutParams = lp
//        val constraintSet = ConstraintSet()
//        constraintSet.clone(constraint_layout)
//        constraintSet.clear(surface_view.id, ConstraintSet.TOP)
//        constraintSet.connect(
//            surface_view.id,
//            ConstraintSet.TOP,
//            ConstraintSet.PARENT_ID,
//            ConstraintSet.TOP,
//            (mScreen.heightPixels - height) / 2
//        )
//        constraintSet.applyTo(constraint_layout)
//        constraint_layout.invalidate()
    }
    private fun setupAudioManager() {
        mAudioManager = getAudioManager()
    }
    private fun setupControls() {
        image_pause.setOnClickListener {
            pasue()
            updateVisibility()
        }
        image_play.setOnClickListener {
            start()
            updateVisibility()
        }
    }
    private fun setupDebugView(bShow: Boolean) {
//        if (bShow) text_debug.visible()
//        else text_debug.inVisible()
    }
    private fun setupMediaPlayer(holder: SurfaceHolder?) {
        if (mMediaPlayer != null) return
        mMediaPlayer = MediaPlayer().also {
            it.setDisplay(holder)
            it.setOnErrorListener(this)
            it.setOnCompletionListener(this)
            it.setOnSeekCompleteListener(this)
        }
    }
    private fun setupSurfaceView() {
        surface_view.holder.addCallback(this)
    }
    private fun setupTimeBar() {
        val position = (mMediaPlayer?.currentPosition ?: 0).toLong()
        val duration = (mMediaPlayer?.duration ?: 0).toLong()
        text_position.text = getStringForTime(mStringBuilder, mFormatter, position)
        text_duration.text = getStringForTime(mStringBuilder, mFormatter, duration)
        time_bar.setPosition(position)
        time_bar.setDuration(duration)
    }
    private fun setupToolBar(title: String?) {
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setTitle(title)
        }
    }
    private fun start() {
        if (isInPlaybackState()) {
            mMediaPlayer?.start()
            mCurrentState = STATE_PLAYING
            updateVisibility()
        }
        mTargetState = STATE_PLAYING
    }
    private fun stopPlayback() {
        mMediaPlayer?.apply {
            stop()
            release()
            mMediaPlayer = null
            mCurrentState = STATE_IDLE
            mTargetState = STATE_IDLE
            mAudioManager.abandonAudioFocus(null)
            updateVisibility()
        }
    }
    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        Log.e(TAG, "[surfaceChanged] $width $height")
        mSurfaceWidth = width
        mSurfaceHeight = height
        val isValidState = (mTargetState == STATE_PLAYING)
        val hasValidSize = (mVideoWidth == width && mVideoHeight == height)
        if (mMediaPlayer != null && isValidState && hasValidSize) {
            start()
        }
    }
    override fun surfaceCreated(holder: SurfaceHolder?) {
        openVideo()
    }
    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        release(true)
    }
    private fun touchSeek(c: Int, gestureSize: Float, seek: Boolean) {
        mMediaPlayer?.let {
            var coef = c
            if (coef == 0) coef = 1
            if (abs(gestureSize) < 1) return
            if (mTouchAction != TOUCH_NONE && mTouchAction != TOUCH_SEEK) return
            mTouchAction = TOUCH_SEEK
            val length = it.duration
            val time = it.currentPosition
            var jump = (Math.signum(gestureSize) * (600000 * Math.pow(gestureSize / 8.0, 4.0) + 3000) / coef).toInt()
            if ((jump > 0) && ((time + jump) > length))
                jump = (length - time);
            if ((jump < 0) && ((time + jump) < 0))
                jump = -time;
            text_debug.text = String.format(
                "%s%s (%s)%s",
                if (jump >= 0) "+" else "",
                jump.toLong().millisToString(),
                (time + jump).toLong().millisToString(),
                if (coef > 1) String.format(" x%.1g", 1.0 / coef) else ""
            )
//        Log.e(
//            TAG, String.format(
//                "%s%s (%s)%s",
//                if (jump >= 0) "+" else "",
//                jump.toLong().millisToString(),
//                (time + jump).toLong().millisToString(),
//                if (coef > 1) String.format(" x%.1g", 1.0 / coef) else ""
//            )
//        )
            //Log.e(TAG, "[touchsSeek] jump -> $jump")
            if (seek && length > 0) {
                seek(time + jump);
                text_debug.text = ""
            }
        }
    }
    private fun touchVertical(y: Float) {
        val rightAction = mTouchX.toInt() > 4 * mScreen.widthPixels / 7f
        if (rightAction) {
            //Log.e(TAG, "[touchVertical] Touch the action on the right side of the screen to change the playback volume")
            touchVolumeChange(y)
            return
        }
        val leftAction = !rightAction && mTouchX.toInt() < (3 * mScreen.widthPixels / 7f)
    }
    private fun touchVolumeChange(y: Float) {
        if (mTouchAction != TOUCH_NONE && mTouchAction != TOUCH_VOLUME) return
        val delta = -(y / mScreen.heightPixels.toFloat() * mAudioMax)
        //Log.e(TAG, "[touchVolumeChange] Volume delta value is $delta")
        mVol += delta
        val vol = Math.min(Math.max(mVol, 0f), mAudioMax * 1f)
        if (delta != 0f) {
            if (vol > mAudioMax) {
            } else {
                setAudioVolume(vol.toInt())
            }
        }
    }
    private fun updateVisibility() {
        mMediaPlayer?.let {
            when (mCurrentState) {
                STATE_PLAYING -> {
                    image_pause.visible()
                    image_play.inVisible()
                    Log.e(TAG, "[updateVisibility] ${image_play.visibility}")
                }
                STATE_PAUSED -> {
                    image_play.visible()
                    image_pause.inVisible()
                }
                else -> {
                }
            }
        }
    }
    companion object {
        private const val STATE_ERROR = -1
        private const val STATE_IDLE = 0
        private const val STATE_PREPARING = 1
        private const val STATE_PREPARED = 2
        private const val STATE_PLAYING = 3
        private const val STATE_PAUSED = 4
        private const val STATE_PLAYBACK_COMPLETED = 5
        private const val TOUCH_NONE = 0
        private const val TOUCH_VOLUME = 1
        private const val TOUCH_BRIGHTNESS = 2
        private const val TOUCH_MOVE = 3
        private const val TOUCH_SEEK = 4
        private const val TOUCH_IGNORE = 5

        private const val TAG = "Media/VideoPlayer"
    }
}