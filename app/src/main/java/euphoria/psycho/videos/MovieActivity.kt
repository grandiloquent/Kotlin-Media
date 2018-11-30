package euphoria.psycho.videos

import android.app.ActionBar
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import euphoria.psycho.common.ApiHelper
import euphoria.psycho.common.getAudioManager


class MovieActivity : AppCompatActivity() {


    private var mUri: Uri? = null
    private var mTreatUpAsBack = false
    private lateinit var mMoviePlayer: MoviePlayer
    private var mFinishOnCompletion = false

    private fun createShareIntent(): Intent {
        return Intent(Intent.ACTION_SEND).apply {
            type = "video/*"
            putExtra(Intent.EXTRA_STREAM, mUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_ACTION_BAR)
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
        setContentView(R.layout.movie_view)
        val rootView = findViewById<RelativeLayout>(R.id.movie_view_root)
        setSystemUiVisibility(rootView)
        initializeActionBar(intent)
        mFinishOnCompletion = intent.getBooleanExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true)
        intent.data?.let {
            mMoviePlayer = object :
                MoviePlayer(rootView, this@MovieActivity, it, savedInstanceState, !mFinishOnCompletion) {
                override fun onCompletion(mp: MediaPlayer?) {
                    if (mFinishOnCompletion) finish()
                }
            }
        } ?: run {
            finish()
            return
        }

        if (intent.hasExtra(MediaStore.EXTRA_SCREEN_ORIENTATION)) {
            val orientation =
                intent.getIntExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
            if (orientation != requestedOrientation) {
                requestedOrientation = orientation
            }
        }
        val layoutParams = window.attributes
        layoutParams.buttonBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_OFF
        layoutParams.flags = layoutParams.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
        window.attributes = layoutParams
        window.setBackgroundDrawable(null)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.movie, menu)
//        val shareItem = menu.findItem(R.id.action_share)
//        if (ContentResolver.SCHEME_CONTENT.equals(mUri?.scheme)) {
//            shareItem.show()
//            (shareItem.actionProvider as ShareActionProvider).setShareIntent(createShareIntent())
//        } else shareItem.hide()
        return true
    }

    override fun onDestroy() {
        mMoviePlayer.onDestroy()
        super.onDestroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return mMoviePlayer.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        return mMoviePlayer.onKeyUp(keyCode, event) || super.onKeyUp(keyCode, event)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_share -> {
                startActivity(Intent.createChooser(createShareIntent(), getString(R.string.share)))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onPause() {
        mMoviePlayer.onPause()
        super.onPause()

    }

    override fun onResume() {
        mMoviePlayer.onResume()
        super.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.let {
            mMoviePlayer.onSaveInstanceState(it)
        }
    }

    override fun onStart() {
        getAudioManager().requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
        super.onStart()
    }

    override fun onStop() {
        getAudioManager().abandonAudioFocus(null)
        super.onStop()

    }

    private fun setActionBarLogoFromIntent(intent: Intent) {
        intent.getParcelableExtra<Bitmap>(KEY_LOGO_BITMAP)?.let {

            actionBar?.setLogo(BitmapDrawable(resources, it))
        }

    }

    private fun setSystemUiVisibility(rootView: View) {
        if (ApiHelper.HAS_VIEW_SYSTEM_UI_FLAG_LAYOUT_STABLE) {
            rootView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        }
    }

    private fun initializeActionBar(intent: Intent) {
        mUri = intent.data
        actionBar?.let {
            it.setDisplayOptions(
                ActionBar.DISPLAY_HOME_AS_UP,
                ActionBar.DISPLAY_HOME_AS_UP
            )
            intent.getStringExtra(Intent.EXTRA_TITLE)?.apply {
                it.title = this
            }

        }

    }

    companion object {
        const val KEY_LOGO_BITMAP = "logo-bitmap"
        const val KEY_TREAT_UP_AS_BACK = "treat-up-as-back"
    }
}