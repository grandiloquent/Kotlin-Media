package euphoria.psycho.media.utils

import android.content.Context
import euphoria.psycho.common.BlobCache
import euphoria.psycho.media.getBytes
import euphoria.psycho.common.BlobCache.LookupRequest
import euphoria.psycho.media.utils.BytesBufferPool.BytesBuffer
import java.io.IOException
import java.nio.ByteBuffer


class ImageCacheService(private val context: Context) {

    private val mCache: BlobCache = CacheManager.getCache(
        context,
        IMAGE_CACHE_FILE,
        IMAGE_CACHE_MAX_ENTRIES,
        IMAGE_CACHE_MAX_BYTES,
        IMAGE_CACHE_VERSION
    )


    fun getImageData(path: String, timeModified: Long, type: Int, buffer: BytesBuffer): Boolean {
        val key = makeKey(path, timeModified, type)
        val cacheKey = Utils.crc64Long(key)
        try {
            val request = LookupRequest()
            request.key = cacheKey
            request.buffer = buffer.data
            synchronized(mCache) {
                if (!mCache.lookup(request)) return false
            }
            if (isSameKey(key, request.buffer)) {
                buffer.data = request.buffer
                buffer.offset = key.size
                buffer.length = request.length - buffer.offset
                return true
            }
        } catch (ex: IOException) {

        }

        return false
    }

    fun clearImageData(path: String, timeModified: Long, type: Int) {
        val key = makeKey(path, timeModified, type)
        val cacheKey = Utils.crc64Long(key)
        synchronized(mCache) {
            try {
                mCache.clearEntry(cacheKey)
            } catch (ex: IOException) {

            }

        }
    }

    fun putImageData(path: String, timeModified: Long, type: Int, value: ByteArray) {
        val key = makeKey(path, timeModified, type)
        val cacheKey = Utils.crc64Long(key)
        val buffer = ByteBuffer.allocate(key.size + value.size)
        buffer.put(key)
        buffer.put(value)
        synchronized(mCache) {
            try {
                mCache.insert(cacheKey, buffer.array())
            } catch (ex: IOException) {

            }

        }
    }

    companion object {
        private const val IMAGE_CACHE_FILE = "imgcache"
        private const val IMAGE_CACHE_MAX_ENTRIES = 5000
        private const val IMAGE_CACHE_MAX_BYTES = 200 * 1024 * 1024
        private const val IMAGE_CACHE_VERSION = 7

        private fun makeKey(path: String, timeModified: Long, type: Int): ByteArray {
            return ("$path+$timeModified+$type").getBytes()
        }

        private fun isSameKey(key: ByteArray, buffer: ByteArray): Boolean {
            val n = key.size
            if (buffer.size < n) {
                return false
            }
            for (i in 0 until n) {
                if (key[i] != buffer[i]) {
                    return false
                }
            }
            return true
        }
    }
}