package  euphoria.psycho.common

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat

val Context.widthPixels get() = resources.displayMetrics.widthPixels
val Context.heightPixels get() = resources.displayMetrics.heightPixels

fun Context.needPermission(vararg permissions: String): Boolean {
    return permissions.any { ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED }
}

fun Context.toast(id: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, id, length).show()
}

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}