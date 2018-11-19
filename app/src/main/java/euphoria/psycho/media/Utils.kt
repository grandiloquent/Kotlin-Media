package euphoria.psycho.media

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import euphoria.psycho.media.C.photoExtensions

object C {


    const val EXTRA_BUCKET_ID = "EXTRA_BUCKET_ID"
    const val EXTRA_FILE_TYPE = "EXTRA_FILE_TYPE"
    const val EXTRA_SHOW_GIF = "SHOW_GIF"
    const val ALL_PHOTOS_BUCKET_ID = "ALL_PHOTOS_BUCKET_ID"
    const val MEDIA_TYPE_IMAGE = 1
    const val MEDIA_TYPE_VIDEO = 3
    const val FILE_TYPE_MEDIA = 1
    const val DEFAULT_MAX_COUNT = -1
    const val FILE_TYPE_DOCUMENT = 2
    const val REQUEST_CODE_MEDIA_DETAIL = 235

    val photoExtensions: Array<String> get() = arrayOf(".jpg", ".png", ".jpeg", ".bmp", ".webp")

}

val videoExtensions: Array<String> get() = arrayOf(".mp4", ".mkv", ".webm", ".avi", ".3gp", ".mov", ".m4v", ".3gpp")
val audioExtensions: Array<String> get() = arrayOf(".mp3", ".wav", ".wma", ".ogg", ".m4a", ".opus", ".flac", ".aac")
val rawExtensions: Array<String> get() = arrayOf(".dng", ".orf", ".nef")

fun Context.displayMetrics(): DisplayMetrics {

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        DisplayMetrics().also {
            windowManager().defaultDisplay.getRealMetrics(it)
        }
    } else {
        resources.displayMetrics
    }
}

fun Context.windowManager(): WindowManager {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getSystemService(WindowManager::class.java)
    } else {
        getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }
}

fun String.isImage(): Boolean {
    return photoExtensions.any { endsWith(it, true) }
}

fun contains(types: Array<String>, path: String): Boolean {
    for (string in types) {
        if (path.toLowerCase().endsWith(string)) return true
    }
    return false
}

fun AppCompatActivity.checkStoragePermission(
    savedInstanceState: Bundle?,
    through: ((Bundle?) -> Unit)?,
    requestCode: Int = 100
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (!permissions.all {
                ContextCompat.checkSelfPermission(
                    this,
                    it
                ) == PackageManager.PERMISSION_GRANTED
            }
        ) {

            requestPermissions(permissions, requestCode)

        } else through?.invoke(savedInstanceState)
    } else through?.invoke(savedInstanceState)
}

fun String.getBytes(): ByteArray {
    val result = ByteArray(this.length * 2)
    var output = 0
    for (ch in toCharArray()) {
        result[output++] = (ch.toInt() and 0xFF).toByte()
        result[output++] = (ch.toInt() shr 8).toByte()
    }
    return result
}

fun canLoadImage(fragment: Fragment?): Boolean {
    if (fragment == null) {
        return true
    }

    val activity = fragment.activity

    return canLoadImage(activity)
}

fun canLoadImage(context: Context?): Boolean {
    if (context == null) {
        return true
    }

    if (context !is Activity) {
        return true
    }

    val activity = context as Activity?
    return canLoadImage(activity)
}

fun canLoadImage(activity: Activity?): Boolean {
    if (activity == null) {
        return true
    }

    val destroyed = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed

    return !(destroyed || activity.isFinishing)

}
