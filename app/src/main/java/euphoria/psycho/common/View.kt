package euphoria.psycho.common

/*
*
* https://developer.android.com/reference/android/view/View
*
 */
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.util.Log
import android.view.View

private const val TAG = "common/View"

fun View.visible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.setSystemUiVisibilityCompat() {
    try {
        View::class.java.getDeclaredField("SYSTEM_UI_FLAG_LAYOUT_STABLE")
        systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
    } catch (ignored: Exception) {
        Log.e(TAG, "[setSystemUiVisibilityCompat] ${ignored.message}")
    }


}

fun View.inVisible() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

fun View.showViewAnimated(duration: Long) {
    if (visibility == View.VISIBLE) return
    if (!isLaidOut) {
        visibility = View.VISIBLE
        return
    }
    animate().alpha(1f).setDuration(duration).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            alpha = 0f
            visibility = View.VISIBLE
        }
    })
}

fun View.hideViewAnimated(duration: Long) {
    if (visibility == View.GONE) return
    if (!isLaidOut) {
        visibility == View.GONE
        return
    }
    animate()
        .alpha(0f)
        .setDuration(duration)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                visibility = View.GONE
            }
        })


}