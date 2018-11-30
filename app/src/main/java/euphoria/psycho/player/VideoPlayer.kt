package euphoria.psycho.player

import android.media.AudioManager
import android.media.MediaFormat
import android.media.MediaPlayer
import android.media.TimedText
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import euphoria.psycho.common.*
import euphoria.psycho.videos.R
import kotlinx.android.synthetic.main.activity_player.*
import java.io.File
import java.io.FileFilter
import java.text.Collator
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs


class VideoPlayer : AppCompatActivity(), SurfaceHolder.Callback, MediaPlayer.OnErrorListener,
    MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnTimedTextListener,
    TimeBar.OnScrubListener,
    MediaPlayer.OnSeekCompleteListener {

    /*
    https://developer.android.com/reference/android/media/MediaPlayer.html
     */

    private lateinit var mAudioManager: AudioManager
    private val mStringBuilder = StringBuilder()
    private val mFormatter = Formatter(mStringBuilder, Locale.getDefault())
    private val mHandler = Handler()
    private val mPlayList = ArrayList<String>()
    private val mScreen = DisplayMetrics()
    private var mAudioMax = 0
    private var mCurrentPath: String? = null
    private var mCurrentPosition = 0
    private var mCurrentState = STATE_IDLE
    private var mDragging = false
    private var mHasPaused = false
    private var mMaxVolume = 0
    private var mMediaPlayer: MediaPlayer? = null
    private var mRealHeight = 0
    private var mSurfaceHeight = 0
    private var mSurfaceWidth = 0
    private var mSurfaceYDisplayRange = 0
    private var mTargetState = STATE_IDLE
    private var mThreshold = 0
    private var mVideoHeight = 0
    private var mVideoWidth = 0
    private var mSeekEndOffset = 0
    private var mIsFullScreen = false
    private var mTouchX = -1f
    private var mTouchY = -1f
    private var mInitTouchY = 0f
    private var mTouchAction = TOUCH_IGNORE
    private var mSubTitleHandler = Handler()


    private val mProgressBarChecker = Runnable {
        scheduleUpdateProgress()
    }
    private val mUiChecker = Runnable { hideUi() }


    private fun hideUi() {
        linear_controller.inVisible()
        hideSystemUI(true)
        mHandler.removeCallbacks(mProgressBarChecker)
        mIsFullScreen = false
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
        setupVariables()
        setupSurfaceView()
        setupControls()
        start()
        mCurrentPath?.apply { setupPlaylist(this) }
        time_bar.addListener(this)
    }

    private fun isInPlaybackState(): Boolean {
        return mMediaPlayer != null &&
                mCurrentState != STATE_ERROR &&
                mCurrentState != STATE_IDLE &&
                mCurrentState != STATE_PREPARING
    }

    private fun jumpToNext(bNext: Boolean = false) {
        if (bNext) {
            val position = mPlayList.indexOf(mCurrentPath)
            if (position + 1 >= mPlayList.size) return
            mCurrentPath = mPlayList[position + 1]
        } else {
            val position = mPlayList.indexOf(mCurrentPath)
            if (position - 1 < 0) return
            mCurrentPath = mPlayList[position - 1]
        }
        mMediaPlayer?.let {
            if (isInPlaybackState()) {
                mCurrentState = STATE_IDLE
                it.stop()
                it.reset()
            }
            it.setDataSource(mCurrentPath)
            it.prepareAsync()
        }
    }

    override fun onCompletion(mp: MediaPlayer?) {
        mCurrentState = STATE_PLAYBACK_COMPLETED
        mTargetState = STATE_PLAYBACK_COMPLETED
        mp?.apply {
            seekTo(0)
            pause()
            mCurrentState = STATE_PAUSED
            updateVisibility()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        // Fix the problem of blocking the bottom control after the navigation bar is redisplayed
        frame_root.setPadding(0, 0, 0, getSoftButtonsBarHeight())
        hideUi()
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
            R.id.action_to_srt -> {
                val smiFile = File(mCurrentPath?.substringBeforeLast('.') + ".smi")
                if (smiFile.isFile) {

                }


            }
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
        toast("$mVideoWidth x $mVideoHeight", false)
        setupTimeBar()
        openSubtitle()
        if (mVideoHeight != 0 && mVideoWidth != 0) {
            setSurfaceViewSize()
            // Log.e(TAG, "[onPrepared]  mSurfaceHeight $mSurfaceHeight mSurfaceWidth $mSurfaceWidth")
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

    }

    override fun onTimedText(mp: MediaPlayer?, text: TimedText?) {
        mSubTitleHandler.post {
            text_subtitle.text = text?.text
        }

    }

    // implements change the volume by gesture
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!mIsFullScreen) {
            showUi()
            return true
        }
        val x_changed = if (mTouchX != -1f && mTouchY != -1f) event.rawX - mTouchX else 0f
        val y_changed = if (x_changed != 0f) event.rawY - mTouchY else 0f
        val xgesturesize = ((x_changed / mScreen.xdpi) * 2.54f);
        val delta_y = Math.max(1f, (Math.abs(mInitTouchY - event.rawY) / mScreen.xdpi + 0.5f) * 2f);
        val coef = Math.abs(y_changed / x_changed);
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mHandler.removeCallbacks(mUiChecker)
                mTouchY = event.rawY
                mInitTouchY = event.rawY
                mTouchX = event.rawX
            }
            MotionEvent.ACTION_MOVE -> {
                if (mTouchAction == TOUCH_IGNORE) return false
                if (mTouchAction != TOUCH_SEEK && coef > 2) {
                    if (Math.abs(y_changed / mSurfaceYDisplayRange) < 0.05)
                        return false;
                    mTouchY = event.rawY;
                    mTouchX = event.rawX;
                } else {
                    seekTouch(Math.round(delta_y), xgesturesize, false)
                }
            }
            MotionEvent.ACTION_UP -> {
                if (mTouchAction == TOUCH_IGNORE) mTouchAction = TOUCH_NONE;
                if (mTouchAction == TOUCH_SEEK)
                    seekTouch(Math.round(delta_y), xgesturesize, true)
                scheduleHideUi()
                text_debug.text = ""
                mTouchX = -1f;
                mTouchY = -1f;
            }
        }
        return false
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
        Log.e("TAG", "[openSubtitle]")

        val subtitle = mCurrentPath?.substringBeforeLast('.') + ".srt"
        if (subtitle.isFile()) {
            try {
                mMediaPlayer?.addTimedTextSource(subtitle, MediaFormat.MIMETYPE_TEXT_SUBRIP)
            } catch (ignored: Exception) {
                Log.e("TAG", "[openSubtitle] ${ignored.message}")
            }


            mMediaPlayer?.let {
                val index =
                    it.trackInfo.findIndex { item -> item.trackType == MediaPlayer.TrackInfo.MEDIA_TRACK_TYPE_TIMEDTEXT }
                        ?: 0
                it.selectTrack(index)

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
                setOnSeekCompleteListener(that)
                setOnTimedTextListener(that)
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

    private fun pause() {
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

    private fun release(clearTargetState: Boolean) {
        mMediaPlayer?.apply {
            // Resets the MediaPlayer to its uninitialized state.
            // After calling this method, you will have to initialize
            // it again by setting the data source and calling prepare().
            reset()
            release()
            mMediaPlayer = null
            mCurrentState = STATE_IDLE
            if (clearTargetState) {
                mTargetState = STATE_IDLE
            }
        }
    }

    // Hide status bar, ActionBar, navigation bar
    private fun scheduleHideUi() {
        mHandler.postDelayed(mUiChecker, DELAY_UI_CHECK)
    }

    private fun scheduleUpdateProgress() {
        if (mIsFullScreen) {
            val pos = (mMediaPlayer?.currentPosition ?: 0).toLong()
            time_bar.setPosition(pos)
            text_position.text = getStringForTime(mStringBuilder, mFormatter, pos)
            mHandler.postDelayed(mProgressBarChecker, (1000 - (pos % 1000)))
        }
    }

    private fun seek(time: Int) {
        mMediaPlayer?.seekTo(time)
    }

    private fun seekTouch(cf: Int, gestureSize: Float, seek: Boolean) {
        var coef = cf
        if (coef == 0)
            coef = 1
        if (abs(gestureSize) < 1)
            return
        if (mTouchAction != TOUCH_NONE && mTouchAction != TOUCH_SEEK)
            return
        mTouchAction = TOUCH_SEEK
        val length = mMediaPlayer?.duration ?: 0
        val time = mMediaPlayer?.currentPosition ?: 0
        var jump = (Math.signum(gestureSize) * (600000 * Math.pow(gestureSize / 8.0, 4.0) + 3000) / coef).toInt()
        if ((jump > 0) && ((time + jump) > length))
            jump = (length - time)
        if ((jump < 0) && ((time + jump) < 0))
            jump = -time;
        if (seek && length > 0)
            seek(time + jump)
        if (length > 0)
            text_debug.text = String.format(
                "%s%s (%s)%s",
                if (jump >= 0) "+" else "",
                getStringForTime(mStringBuilder, mFormatter, jump.toLong()),
                getStringForTime(mStringBuilder, mFormatter, (time + jump).toLong()),
                if (coef > 1) String.format(" x%.1g", 1.0 / coef) else ""
            )
    }

    private fun setSurfaceViewSize() {
        val width = mScreen.widthPixels
        val height = (mVideoHeight.toFloat() / mVideoWidth.toFloat() * mScreen.widthPixels.toFloat()).toInt()
        val lp = FrameLayout.LayoutParams(width, height)
        lp.gravity = Gravity.TOP
        lp.setMargins(0, (mScreen.heightPixels - height) / 2, 0, 0)
        surface_view.layoutParams = lp
        val subTitleLayoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT
        )
        text_subtitle.setPadding(0, (mScreen.heightPixels - height) / 2 + height, 0, 0)
    }

    private fun setupControls() {
        image_pause.setOnClickListener {
            if (mCurrentState == STATE_PLAYING) {
                pause()
            } else if (mCurrentState == STATE_PAUSED) {
                start()
            }
            updateVisibility()
        }
        image_next.setOnClickListener {
            jumpToNext(true)
        }
        image_prev.setOnClickListener {
            jumpToNext(false)
        }
    }

    private fun setupPlaylist(dir: String) {
        val directory = File(dir.substringBeforeLast('/'))
        if (!directory.isDirectory) return
        val files = directory.listFiles(FileFilter { it.isFile && it.name.isVideoFast() })
        if (files == null) return
        files.sortWith(compareBy(Collator.getInstance(Locale.CHINA)) { it.name.toLowerCase() })
        files.forEach { mPlayList.add(it.absolutePath) }
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


    private fun setupVariables() {
        mMaxVolume = mAudioManager.getMusicMaxVolume()
        mSeekEndOffset = dp2px(50f).toInt()
        mThreshold = 80
    }

    private fun showUi() {
        linear_controller.visible()
        showSystemUI(true)
        mIsFullScreen = true
        mHandler.post(mProgressBarChecker)
        scheduleHideUi()
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

    private fun updateVisibility() {
        mMediaPlayer?.let {


            mCurrentPath?.substringAfterLast('/').apply {

                supportActionBar?.setTitle(this)
            }
            when (mCurrentState) {
                STATE_PLAYING -> {
                    image_pause.setImageResource(android.R.drawable.ic_media_pause)
                }
                STATE_PAUSED -> {
                    image_pause.setImageResource(android.R.drawable.ic_media_play)
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
        private const val TOUCH_SEEK = 4
        private const val TOUCH_IGNORE = 5
        private const val DELAY_UI_CHECK = 5000L

        private const val TAG = "Media/VideoPlayer"
    }
}