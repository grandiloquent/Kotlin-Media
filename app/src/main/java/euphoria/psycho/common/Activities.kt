package com.euphoria.psycho.elf.common

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.view.Surface
import android.view.View
import androidx.appcompat.app.AppCompatActivity


fun Activity.calculateScreenOrientation(): Int {
    val displayRotation = getDisplayRotation()
    var standard = displayRotation < 180
    if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        if (standard)
            return ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        else return ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
    } else {
        if (displayRotation == 90 || displayRotation == 270) {
            standard = !standard
        }
        return if (standard) ActivityInfo.SCREEN_ORIENTATION_PORTRAIT else ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT
    }
}
fun Activity.getDisplayRotation(): Int {
    val rotation = windowManager.defaultDisplay.rotation
    return when (rotation) {
        Surface.ROTATION_0 -> 0
        Surface.ROTATION_90 -> 90
        Surface.ROTATION_180 -> 180
        Surface.ROTATION_270 -> 270
        else -> 0
    }
}
fun AppCompatActivity.hideSystemUI(toggleActionBarVisibility: Boolean) {
    if (toggleActionBarVisibility) {
        supportActionBar?.hide()
    }
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_IMMERSIVE
}
fun AppCompatActivity.removeFragmentByTag(tag: String) {
    supportFragmentManager.findFragmentByTag(tag)?.let {
        supportFragmentManager.beginTransaction().remove(it).commit()
    }
}
fun AppCompatActivity.showSystemUI(toggleActionBarVisibility: Boolean) {
    if (toggleActionBarVisibility) {
        supportActionBar?.show()
    }
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
}
