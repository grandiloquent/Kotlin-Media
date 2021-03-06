package  euphoria.psycho.common

import android.net.Uri
import android.text.TextUtils
import java.io.File
import java.io.FileNotFoundException

fun File.changeExtension(extension: String): String {
    return absolutePath.substringBeforeLast('.') + if (extension.startsWith(".")) extension else (".$extension")
}


fun File.combine(fileName: String): String {
    return "$absolutePath/$fileName"
}


fun File.combine(vararg fileNames: String): String {

    var result = "$absolutePath/"
    for (f in fileNames) {
        result = "$result/$f"
    }
    return result
}


fun File.getDirectoryName(): String {
    return parentFile.absolutePath
}


fun File.getExtension(): String {
    return name.substringAfterLast('.')
}


fun File.getFileName(): String {
    return name
}


fun File.getFileNameWithoutExtension(): String {
    return name.substringBeforeLast('.')
}

fun File.getFullPath(): String {
    return absolutePath
}


private fun buildFile(parent: File, name: String, ext: String?): File {
    return if (TextUtils.isEmpty(ext)) {
        File(parent, name)
    } else {
        File(parent, "$name.$ext")
    }
}

fun File.buildUniqueFile(): File {
    val parent = parentFile
    val ext = extension
    val name = nameWithoutExtension
    return parent.buildUniqueFileWithExtension(name, ext)
}

fun File.buildUniqueFileWithExtension(name: String, ext: String?): File {
    var file = buildFile(this, name, ext)
    // If conflicting file, try adding counter suffix
    var n = 0
    while (file.exists()) {
        if (n++ >= 320) {
            throw FileNotFoundException("Failed to create unique file")
        }
        file = buildFile(this, "$name (${n.toString().padStart(3, '0')})", ext)
    }
    return file
}


//fun File.getFileList(sort: Int = SORT_BY_NAME, bShowHidden: Boolean = true): ArrayList<FileBean>? {
//    if (!isDirectory) return null
//    var files = if (!bShowHidden) listFiles(FilenameFilter { file, s -> !s.startsWith(".") }) else listFiles()
//    if (files == null) return null
//    when (sort) {
//        SORT_BY_DATE_MODIFIED -> files.sortWith(compareBy<File> { it.isFile }.thenByDescending { it.lastModified() })
//        SORT_BY_SIZE -> files.sortWith(compareBy<File> { it.isFile }.thenByDescending { it.length() })
//        else -> files.sortWith(compareBy<File> { it.isFile }.thenBy { it.name })
//    }
//    val arrayList = ArrayList<FileBean>()
//    files.indices.forEach {
//        arrayList.add(FileBean(
//                files[it].absolutePath,
//                files[it].name,
//                files[it].isFile,
//                files[it].length()
//        ));
//    }
//    return arrayList
//}
fun File.listVideoFiles(): List<File>? {
    return listFiles()?.filter { it.isFile && it.name.isVideoFast() }?.sortedBy { it.name }
}

fun File.toUri(): Uri {
    return Uri.fromFile(this)
}