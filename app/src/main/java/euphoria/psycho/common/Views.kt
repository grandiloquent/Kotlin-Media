package euphoria.psycho.common

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

fun View.visible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
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