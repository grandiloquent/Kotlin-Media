package euphoria.psycho.common

import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.media.AudioManager
import android.media.AudioManager.STREAM_MUSIC
import android.os.Build
import android.os.Message
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.annotation.RequiresApi


fun Context.getPackageIcon(): Drawable? {
    try {
        return packageManager.getApplicationIcon(packageName)
    } catch (e: PackageManager.NameNotFoundException) {
        return null
    }

}

fun Context.toast(message: String, bShort: Boolean = true) {
    if (bShort)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    else
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

}

fun Context.dp2px(dp: Float): Float {
    return dp * (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun Context.px2dp(px: Float): Float {
    return px / (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun Context.getDefaultSharedPreferences(): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(this)
}

fun Context.inflate(resId: Int): View {
    return LayoutInflater.from(this).inflate(resId, null)
}

fun Context.getInputMethodManager(): InputMethodManager {
    return if (Build.VERSION.SDK_INT >= 23)
        getSystemService(InputMethodManager::class.java)
    else getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
}

fun Context.getClipboardManager(): ClipboardManager {
    return if (Build.VERSION.SDK_INT >= 23)
        getSystemService(ClipboardManager::class.java)
    else getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
}

fun Context.getAudioManager(): AudioManager {
    return if (Build.VERSION.SDK_INT >= 23)
        getSystemService(AudioManager::class.java)
    else getSystemService(Context.AUDIO_SERVICE) as AudioManager
}