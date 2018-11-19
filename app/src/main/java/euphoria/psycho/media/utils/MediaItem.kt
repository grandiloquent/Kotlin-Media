package euphoria.psycho.media.utils


abstract class MediaItem {
    companion object {
        const val TYPE_MICROTHUMBNAIL = 2

        private const val BYTESBUFFE_POOL_SIZE = 4
        private const val BYTESBUFFER_SIZE = 200 * 1024
        private val sMicroThumbBufferPool = BytesBufferPool(
            BYTESBUFFE_POOL_SIZE,
            BYTESBUFFER_SIZE
        )

        fun getBytesBufferPool(): BytesBufferPool {
            return sMicroThumbBufferPool
        }

    }
}