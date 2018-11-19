package euphoria.psycho.media.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import euphoria.psycho.common.BitmapUtils
import euphoria.psycho.common.ThreadPool.Job
import euphoria.psycho.common.ThreadPool.JobContext

abstract class ImageCacheRequest(
    private val path: String,
    private val service: ImageCacheService,
    private val timeModified: Long,
    private val type: Int,
    private val targetSize: Int
) : Job<Bitmap> {

    abstract fun onDecodeOriginal(jc: JobContext, targetSize: Int): Bitmap?

    override fun run(jc: JobContext): Bitmap? {
        val buffer = MediaItem.getBytesBufferPool().get()
        try {
            val found = service.getImageData(path, timeModified, type, buffer)
            if (jc.isCancelled) return null
            if (found) {
                val options = BitmapFactory.Options().apply {
                    inPreferredConfig = Bitmap.Config.ARGB_8888
                }
                return if (type == MediaItem.TYPE_MICROTHUMBNAIL) {
                    DecodeUtils.decodeUsingPool(
                        jc,
                        buffer.data, buffer.offset, buffer.length, options
                    );
                } else {
                    DecodeUtils.decodeUsingPool(
                        jc,
                        buffer.data, buffer.offset, buffer.length, options
                    );
                }
            }
        } finally {
            MediaItem.getBytesBufferPool().recycle(buffer)
        }
        var bitmap = onDecodeOriginal(jc, type)
        if (jc.isCancelled) return null
        if (bitmap == null) {
            Log.w(TAG, "decode original failed")
            return null
        }
        if (type == MediaItem.TYPE_MICROTHUMBNAIL) {
            bitmap = BitmapUtils.resizeAndCropCenter(bitmap, targetSize, true)
        } else {
            bitmap = BitmapUtils.resizeDownBySideLength(bitmap, targetSize, true)
        }
        if (jc.isCancelled) return null
        val array = BitmapUtils.compressToBytes(bitmap)
        if (jc.isCancelled) return null
        service.putImageData(path, timeModified, type, array)
        return bitmap
    }

    companion object {
        private const val TAG = "Media/ImageCacheRequest"
    }
}
