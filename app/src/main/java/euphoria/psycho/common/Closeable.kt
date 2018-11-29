package euphoria.psycho.common

import java.io.Closeable
import java.lang.Exception

fun Closeable.closeSilently() {
    try {
        close()
    } catch (ignored: Exception) {

    }
}