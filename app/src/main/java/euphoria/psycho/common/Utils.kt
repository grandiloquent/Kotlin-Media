package euphoria.psycho.common

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.text.Editable
import android.view.WindowManager
import android.widget.EditText
import java.io.File
import java.util.*
import android.app.Activity
import android.util.DisplayMetrics


val SDK_INT by lazy {
    Build.VERSION.SDK_INT
}

fun Activity.getSoftButtonsBarHeight(): Int {
    // getRealMetrics is only available with API 17 and +
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val usableHeight = metrics.heightPixels
        windowManager.defaultDisplay.getRealMetrics(metrics)
        val realHeight = metrics.heightPixels
        return if (realHeight > usableHeight)
            realHeight - usableHeight
        else
            0
    }
    return 0
}

fun Context.getActionBarHeight(): Int {
    val ta = theme.obtainStyledAttributes(
        intArrayOf(android.R.attr.actionBarSize)
    )
    return ta.getDimension(0, 0f).toInt()
}

fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun constrainValue(value: Int, min: Int, max: Int): Int {
    return Math.max(min, Math.min(value, max))
}

fun <T> Array<T>.findIndex(predicate: (T) -> Boolean): Int? {

    return indices.find { it -> predicate(this[it]) }
}

fun constrainValue(value: Long, min: Long, max: Long): Long {
    return Math.max(min, Math.min(value, max))
}

fun getStringForTime(builder: StringBuilder, formatter: Formatter, timeMs: Long): String {
    var timeMs = timeMs
    if (timeMs == C.TIME_UNSET) {
        timeMs = 0
    }
    val totalSeconds = (timeMs + 500) / 1000
    val seconds = totalSeconds % 60
    val minutes = totalSeconds / 60 % 60
    val hours = totalSeconds / 3600
    builder.setLength(0)
    return if (hours > 0)
        formatter.format("%d:%02d:%02d", hours, minutes, seconds).toString()
    else
        formatter.format("%02d:%02d", minutes, seconds).toString()
}

fun checkArgument(expression: Boolean) {
    if (!expression) {
        throw IllegalArgumentException()
    }
}

inline fun atMost(sdk: Int, f1: () -> Unit, f2: () -> Unit) {
    if (Build.VERSION.SDK_INT <= sdk) f1()
    else f2.invoke()
}

inline fun more(sdk: Int, f1: () -> Unit, f2: () -> Unit) {
    if (Build.VERSION.SDK_INT > sdk) f1()
    else f2.invoke()
}

fun doDeleteFileAction(context: Context, files: Array<File?>, callback: (() -> Unit)?) {
    files.forEach { deleteFile(context, it) }
    callback?.invoke()
}

private fun deleteFile(context: Context, file: File?) {
    if (file == null) return
    file.delete()
}

fun dialog(
    context: Context,
    content: String?,
    title: String?,
    isForFileName: Boolean = false,
    positiveListener: (Editable?) -> Unit
) {
    val editText = EditText(context)
    editText.maxLines = 1
    editText.setText(content)
    if (isForFileName) {
        content?.let {
            val pos = it.lastIndexOf('.')
            if (pos > -1) {
                editText.setSelection(0, pos)
            }
        }
    }
    val dialog = AlertDialog.Builder(context)
        .setView(editText)
        .setTitle(title)
        .setNegativeButton("取消") { dialog, _ -> dialog.dismiss() }
        .setPositiveButton("确定") { dialog, _ ->
            dialog.dismiss()
            positiveListener(editText.text)
        }.create()
    //  Show the input keyboard for user
    dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    dialog.show()
}

fun renameFile(context: Context, oldFile: File, newFile: File) {
    val newFile = newFile.buildUniqueFile()
    oldFile.renameTo(newFile)
}