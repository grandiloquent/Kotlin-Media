package euphoria.psycho.common

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.text.Editable
import android.view.WindowManager
import android.widget.EditText
import java.io.File

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