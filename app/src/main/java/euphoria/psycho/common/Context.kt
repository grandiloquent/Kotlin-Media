package euphoria.psycho.common

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.media.AudioManager
import android.os.Build
import android.preference.PreferenceManager

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

fun Context.getAudioManager(): AudioManager {
    return if (Build.VERSION.SDK_INT >= 23)
        getSystemService(AudioManager::class.java)
    else getSystemService(Context.AUDIO_SERVICE) as AudioManager
}