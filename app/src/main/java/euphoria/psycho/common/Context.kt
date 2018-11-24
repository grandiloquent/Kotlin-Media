package euphoria.psycho.common

import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.media.AudioManager
import android.os.Build
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Context.getPackageIcon(): Drawable? {
    try {
        return packageManager.getApplicationIcon(packageName)
    } catch (e: PackageManager.NameNotFoundException) {
        return null
    }

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