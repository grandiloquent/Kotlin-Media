package euphoria.psycho.videos

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.audiofx.Virtualizer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog
import euphoria.psycho.common.ApiHelper
import euphoria.psycho.common.isMediaKey


open class MoviePlayer(
    private val rootView: View,
    private val activity: MovieActivity,
    videoUri: Uri,
    saveInstance: Bundle?,
    canReplay: Boolean
) : ControllerOverlay.Listener,
    MediaPlayer.OnErrorListener,
    MediaPlayer.OnCompletionListener {


    private val mBookmaker = Bookmarker(activity.applicationContext)
    private val mContext = activity.applicationContext
    private val mController = MovieControllerOverlay(activity)
    private val mHandler = Handler()
    private val mVideoView: VideoView = rootView.findViewById(R.id.video_view)
    private var mDragging = false
    private var mHasPaused = false
    private var mResumableTime = 0L
    private var mShowing = false
    private var mUri = videoUri
    private var mVideoPosition = 0
    private var mVirtualizer: Virtualizer? = null
    private val mAudioBecomingNoisyReceiver = AudioBecomingNoisyReceiver()
    private var mLastSystemUiVis = 0

    private val mProgressBarChecker = Runnable {
        postProgressCheck()
    }
    private val mPlayingChecker = Runnable {
        if (mVideoView.isPlaying) mController.showPlaying() else postPlayingCheck()
    }

    init {
        mController.apply {
            (rootView as ViewGroup).addView(view)
            setListener(this@MoviePlayer)
            setCanReplay(canReplay)
        }
        mVideoView.apply {
            setOnErrorListener(this@MoviePlayer)
            setOnCompletionListener(this@MoviePlayer)
            setVideoURI(mUri)
            setupVirtualizer()
            setOnTouchListener { v, event ->
                mController.show()
                true
            }
            setOnPreparedListener {
                if (!mVideoView.canSeekForward() || !mVideoView.canSeekBackward()) {
                    mController.setSeekable(false)
                } else {
                    mController.setSeekable(true)
                }
                setProgress()
            }
            postDelayed({ visibility = View.VISIBLE }, BLACK_TIMEOUT)
        }

        setOnSystemUiVisibilityChangListener()
        showSystemUi(false)
        mAudioBecomingNoisyReceiver.register()

        activity.sendBroadcast(Intent(SERVICECMD).also {
            it.putExtra(CMDNAME, CMDPAUSE)
        })

        saveInstance?.let {
            mVideoPosition = it.getInt(KEY_VIDEO_POSITION, 0)
            mResumableTime = it.getLong(KEY_RESUMABLE_TIME, Long.MAX_VALUE)
            mVideoView.start()
            mVideoView.suspend()
            mHasPaused = false
        } ?: run {
            mBookmaker.getBookmark(mUri)?.let {
                showResumeDialog(activity, it)
            } ?: run {
                startVideo()
            }

        }
    }


    override fun onCompletion(mp: MediaPlayer?) {
        mController.showEnded()
        onCompletion()
    }

    private fun onCompletion() {

    }

    fun onDestroy() {
        mVirtualizer?.let {
            it.release()
            mVirtualizer = null
        }
        mVideoView.stopPlayback()
        mAudioBecomingNoisyReceiver.unregister()
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        mHandler.removeCallbacksAndMessages(null)
        mController.showErrorMessage("")
        return false
    }

    override fun onHidden() {
        mShowing = false
        showSystemUi(false)
    }

    fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.repeatCount > 0) return keyCode.isMediaKey()
        return when (keyCode) {
            KeyEvent.KEYCODE_HEADSETHOOK,
            KeyEvent.KEYCODE_MEDIA_PAUSE -> {
                if (mVideoView.isPlaying) pauseVideo()
                else playVideo()
                true
            }
            KeyEvent.KEYCODE_MEDIA_PAUSE -> {
                if (mVideoView.isPlaying) pauseVideo()
                true
            }
            KeyEvent.KEYCODE_MEDIA_PLAY -> {
                if (!mVideoView.isPlaying)
                    playVideo()
                true
            }
            KeyEvent.KEYCODE_MEDIA_PREVIOUS,
            KeyEvent.KEYCODE_MEDIA_NEXT -> {
                true
            }
            else -> false
        }
    }

    fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        return keyCode.isMediaKey()
    }

    fun onPause() {
        mHasPaused = true
        mHandler.removeCallbacksAndMessages(null)
        mVideoPosition = mVideoView.currentPosition
        mBookmaker.setBookmark(mUri, mVideoPosition, mVideoView.duration)
        mVideoView.suspend()
        mResumableTime = System.currentTimeMillis() + RESUMEABLE_TIMEOUT
    }

    override fun onPlayPause() {
        if (mVideoView.isPlaying) {
            pauseVideo()
        } else {
            playVideo()
        }

    }

    override fun onReplay() {
        startVideo()
    }

    fun onResume() {
        if (mHasPaused) {
            mVideoView.seekTo(mVideoPosition)
            mVideoView.resume()
            if (System.currentTimeMillis() > mResumableTime) {
                pauseVideo()
            }
        }
        mHandler.post(mProgressBarChecker)
    }

    fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_VIDEO_POSITION, mVideoPosition)
        outState.putLong(KEY_RESUMABLE_TIME, mResumableTime)
    }

    override fun onSeekEnd(time: Int, trimStartTime: Int, trimEndTime: Int) {
        mDragging = false
        mVideoView.seekTo(time)
        setProgress()
    }

    override fun onSeekMove(time: Int) {
        mVideoView.seekTo(time)
    }

    override fun onSeekStart() {
        mDragging = true
    }

    override fun onShown() {
        mShowing = true
        setProgress()
        showSystemUi(true)
    }

    private fun pauseVideo() {
        mVideoView.pause()
        mController.showPaused()
    }

    private fun playVideo() {
        mVideoView.start()
        mController.showPlaying()
        setProgress()
    }

    private fun postPlayingCheck() {
        mHandler.postDelayed(mPlayingChecker, 250)
    }

    private fun postProgressCheck() {
        val pos = setProgress()
        mHandler.postDelayed(this.mProgressBarChecker, (1000 - (pos % 1000)).toLong())
    }

    private fun setOnSystemUiVisibilityChangListener() {
        if (!ApiHelper.HAS_VIEW_SYSTEM_UI_FLAG_HIDE_NAVIGATION) return
        mVideoView.setOnSystemUiVisibilityChangeListener { visibility ->
            val diff = mLastSystemUiVis xor visibility
            mLastSystemUiVis = visibility
            if (diff and View.SYSTEM_UI_FLAG_HIDE_NAVIGATION !== 0 && visibility and View.SYSTEM_UI_FLAG_HIDE_NAVIGATION == 0) {
                mController.show()
            }
        }
    }

    private fun setProgress(): Int {
        if (mDragging || !mShowing) return 0
        val position = mVideoView.currentPosition
        mController.setTimes(position, mVideoView.duration, 0, 0)
        return position
    }

    private fun setupVirtualizer() {
        val intent = activity.intent
        val virtualize = intent.getBooleanExtra(EXTRA_VIRTUALIZE, false)
        if (virtualize) {
            val session = mVideoView.audioSessionId
            if (session != 0) {
                mVirtualizer = Virtualizer(0, session).also {
                    it.enabled = true
                }
            } else Log.e(TAG, "no audio session to virtualize")
        }
    }

    private fun showResumeDialog(context: Context, bookmark: Int) {
        AlertDialog.Builder(context).also {
            it.setTitle(R.string.resume_playing_title)
                .setMessage(
                    String.format(
                        context.getString(R.string.resume_playing_message),
                        formatDuration(context, bookmark / 1000)
                    )
                )
                .setOnCancelListener { }
                .setPositiveButton(
                    R.string.resume_playing_title
                ) { dialog, which ->
                    mVideoView.seekTo(bookmark)
                    startVideo()
                }
                .setNegativeButton(R.string.resume_playing_restart) { dialog, which ->
                    startVideo()
                }
                .show()
        }
    }

    private fun showSystemUi(visible: Boolean) {
        if (!ApiHelper.HAS_VIEW_SYSTEM_UI_FLAG_LAYOUT_STABLE) return
        var flag = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        if (!visible) {
            flag = flag or (View.STATUS_BAR_HIDDEN or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
        mVideoView.systemUiVisibility = flag
    }

    fun startVideo() {
        val scheme = mUri.scheme
        if ("http".equals(scheme, true) || "rtsp".equals(scheme, true)) {
            mController.showLoading()
            mHandler.removeCallbacks(mPlayingChecker)
            mHandler.postDelayed(mPlayingChecker, 250)
        } else {
            mController.showPlaying()
            mController.hide()
        }
        mVideoView.start()
        setProgress()
    }

    inner class AudioBecomingNoisyReceiver : BroadcastReceiver() {
        fun register() {
            mContext.registerReceiver(this, IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY))
        }

        fun unregister() {
            mContext.unregisterReceiver(this)
        }

        override fun onReceive(context: Context?, intent: Intent?) {
            if (mVideoView.isPlaying) pauseVideo()
        }

    }


    companion object {
        private const val SERVICECMD = "com.android.music.musicservicecommand"
        private const val CMDNAME = "command"
        private const val CMDPAUSE = "pause"
        private const val BLACK_TIMEOUT = 500L
        private const val TAG = "videos/MoviePlayer"
        const val EXTRA_VIRTUALIZE = "virtualize"
        const val KEY_VIDEO_POSITION = "video_position"
        const val KEY_RESUMABLE_TIME = "resumable_time"
        private const val RESUMEABLE_TIMEOUT = 3 * 60 * 1000
        fun formatDuration(context: Context, duration: Int): String {
            val h = duration / 3600
            val m = (duration - h * 3600) / 60
            val s = duration - (h * 3600 + m * 60)
            val durationValue: String
            if (h == 0) {
                durationValue = String.format(context.getString(R.string.details_ms), m, s)
            } else {
                durationValue = String.format(context.getString(R.string.details_hms), h, m, s)
            }
            return durationValue
        }
    }

}