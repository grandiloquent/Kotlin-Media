package  euphoria.psycho.common

import android.content.Context
import android.graphics.Point
import android.media.AudioManager
import android.os.Build
import android.view.WindowManager
import kotlin.properties.Delegates

object Values {

    var context: Context by Delegates.notNull<Context>()
    var musicVolume: Int
        get() = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        set(value) = audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, value, 0)

    val audioManager by lazy {
        if (Build.VERSION.SDK_INT >= 23)
            context.getSystemService(AudioManager::class.java)
        else
            context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    }
    val maxMusicVolume by lazy {
        audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    }
    val navigationBarHeight by lazy {
        if (navigationBarBottom) navigationBarSize.y else 0
    }
    val navigationBarRight by lazy {
        usableScreenSize.x < realScreenSize.x
    }
    val navigationBarSize by lazy {
        when {
            navigationBarRight -> Point(realScreenSize.x - usableScreenSize.x, usableScreenSize.y)
            navigationBarBottom -> Point(usableScreenSize.x, realScreenSize.y - usableScreenSize.y)
            else -> Point()
        }
    }
    val navigationBarBottom by lazy {
        usableScreenSize.y < realScreenSize.y
    }
    val usableScreenSize by lazy {
        val size = Point()

        windowManager.defaultDisplay.getSize(size)
        size
    }
    val navigationBarWidth by lazy {
        if (navigationBarRight) navigationBarSize.x else 0
    }
    val windowManager by lazy {
        if (Build.VERSION.SDK_INT >= 23)
            context.getSystemService(WindowManager::class.java)
        else
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }
    val realScreenSize by lazy {
        val size = Point()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            windowManager.defaultDisplay.getRealSize(size)
        size
    }
}