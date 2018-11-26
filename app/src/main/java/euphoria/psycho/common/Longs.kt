package euphoria.psycho.common

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.math.max
import kotlin.math.min

private val sb by lazy {
    StringBuilder()
}
private val format by lazy {
    (NumberFormat.getInstance(Locale.US) as DecimalFormat).also {
        it.applyPattern("00")
    }
}

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

fun Long.millisToString(): String {
    return millisToString(false, true)
}

fun Long.millisToString(text: Boolean, seconds: Boolean): String {
    var millis = this
    sb.setLength(0)
    if (millis < 0) {
        millis = -millis
        sb.append("-")
    }

    millis /= 1000
    val sec = (millis % 60).toInt()
    millis /= 60
    val min = (millis % 60).toInt()
    millis /= 60
    val hours = millis.toInt()

    if (text) {
        if (hours > 0)
            sb.append(hours).append('h')
        if (min > 0)
            sb.append(min).append("min")
        if ((seconds || sb.length === 0) && sec > 0)
            sb.append(sec).append("s")
    } else {
        if (hours > 0)
            sb.append(hours).append(':').append(format.format(min)).append(':').append(format.format(sec))
        else
            sb.append(min).append(':').append(format.format(sec))
    }
    return sb.toString()
}
