package  euphoria.psycho.common


const val TIME_UNSET = Long.MIN_VALUE + 1
const val SORT_BY_ARTIST = 4096
const val SORT_BY_DATE_MODIFIED = 2
const val SORT_BY_DATE_TAKEN = 8
const val SORT_BY_DURATION = 8192
const val SORT_BY_EXTENSION = 16
const val SORT_BY_FIRST_NAME = 128
const val SORT_BY_MIDDLE_NAME = 256
const val SORT_BY_NAME = 1
const val SORT_BY_NUMBER = 64
const val SORT_BY_PATH = 32
const val SORT_BY_SIZE = 4
const val SORT_BY_SURNAME = 512
const val SORT_BY_TITLE = 2048
const val SORT_DESCENDING = 1024
const val SORT_ASC = 1
const val SORT_DESC = 2
const val MIMETYPE_TEXT_SUBRIP = "application/x-subrip"
const val TIME_END_OF_SOURCE = java.lang.Long.MIN_VALUE
val photoExtensions: Array<String> get() = arrayOf(".jpg", ".png", ".jpeg", ".bmp", ".webp")
val audioExtensions: Array<String> get() = arrayOf(".mp3", ".wav", ".wma", ".ogg", ".m4a", ".opus", ".flac", ".aac")
val rawExtensions: Array<String> get() = arrayOf(".dng", ".orf", ".nef")
val videoExtensions: Array<String> get() = arrayOf(".mp4", ".mkv", ".webm", ".avi", ".3gp", ".mov", ".m4v", ".3gpp")
val achieveExtensions: Array<String> get() = arrayOf(".zip", ".rar", ".7z")
val plainTextExtensions: Array<String> get() = arrayOf(".txt", ".html", ".log", ".css")

object C {
    const val INDEX_UNSET = -1
    const val TIME_UNSET = Long.MIN_VALUE + 1
    const val CHARSET_UTF8 = "UTF-8"

}