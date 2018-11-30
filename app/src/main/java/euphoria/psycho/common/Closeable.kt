package euphoria.psycho.common

import java.io.Closeable

fun Closeable.closeSilently() {
    try {
        close()
    } catch (ignored: Exception) {

    }
}