package euphoria.psycho.common

import android.view.MenuItem

fun MenuItem.hide() {
    if (this.isVisible)
        isVisible = false
}

fun MenuItem.show() {
    if (!this.isVisible) isVisible = true
}