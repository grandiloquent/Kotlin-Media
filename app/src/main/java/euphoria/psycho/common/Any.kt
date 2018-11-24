package euphoria.psycho.common

fun <T> checkNotNull(t: T?): T? {
    if (t == null) {
        throw  NullPointerException()
    }
    return t
}

