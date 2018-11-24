package euphoria.psycho.common

import java.text.DecimalFormat
import java.util.*
import kotlin.math.max
import kotlin.math.min

fun Long.contrain(minValue: Long, maxValue: Long) = max(minValue, min(this, maxValue))

// Format file size for readable
fun Long.formatSize(): String {
    if (this <= 0)
        return "0 B"
    val units = arrayOf("B", "kB", "MB", "GB", "TB")
    val digitGroups = (Math.log10(toDouble()) / Math.log10(1024.0)).toInt()
    return "${DecimalFormat("#,##0.#").format(this / Math.pow(1024.0, digitGroups.toDouble()))} ${units[digitGroups]}"
}

fun Long.getStringForTime(builder: StringBuilder, formatter: Formatter): String {
    var timeMs = this
    if (timeMs == TIME_UNSET) {
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

fun Long.formatForCount(): String {
    return this.toString() + " 个"
}

fun Long.usToMs(): Long {
    return if (this == TIME_UNSET || this == TIME_END_OF_SOURCE) this else this / 1000
}

fun Long.clamp(min: Long, max: Long): Long {
    if (this > max) return max
    return if (this < min) min else this
}