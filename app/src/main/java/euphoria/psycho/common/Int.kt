package euphoria.psycho.common

import android.graphics.Color
import android.view.KeyEvent


private fun convert8BitToLuminanceComponent(component: Double): Double {
    var component = component
    component /= 255.0
    return if (component <= 0.03928) {
        component / 12.92
    } else {
        Math.pow((component + 0.055) / 1.055, 2.4)
    }
}

fun Int.isMediaKey(): Boolean {
    return (this == KeyEvent.KEYCODE_HEADSETHOOK
            || this == KeyEvent.KEYCODE_MEDIA_PREVIOUS
            || this == KeyEvent.KEYCODE_MEDIA_NEXT
            || this == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE
            || this == KeyEvent.KEYCODE_MEDIA_PLAY
            || this == KeyEvent.KEYCODE_MEDIA_PAUSE)
}

fun Int.getLuminance(): Double {
    // http://www.w3.org/TR/WCAG20-TECHS/G17.html
    val r = convert8BitToLuminanceComponent(Color.red(this).toDouble())
    val g = convert8BitToLuminanceComponent(Color.green(this).toDouble())
    val b = convert8BitToLuminanceComponent(Color.blue(this).toDouble())
    return r * 0.2126 + g * 0.7152 + b * 0.0722
}