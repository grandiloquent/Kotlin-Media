package euphoria.psycho.videos

import android.content.Context
import android.net.Uri
import android.util.Log
import euphoria.psycho.common.CacheManager
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream

class Bookmarker(private val context: Context) {

    fun setBookmark(uri: Uri, bookmark: Int, duration: Int) {
        try {
            val cache = CacheManager.getCache(
                context, BOOKMARK_CACHE_FILE, BOOKMARK_CACHE_MAX_ENTRIES,
                BOOKMARK_CACHE_MAX_BYTES, BOOKMARK_CACHE_VERSION
            )
            val byteArrayOutputStream = ByteArrayOutputStream()
            DataOutputStream(byteArrayOutputStream).apply {
                writeUTF(uri.toString())
                writeInt(bookmark)
                writeInt(duration)
                flush()
                cache.insert(uri.hashCode().toLong(), byteArrayOutputStream.toByteArray())
            }

        } catch (t: Throwable) {
            Log.e(TAG, "setBookmark failed", t)
        }
    }

    fun getBookmark(uri: Uri): Int? {
        try {
            val cache = CacheManager.getCache(
                context, BOOKMARK_CACHE_FILE, BOOKMARK_CACHE_MAX_ENTRIES,
                BOOKMARK_CACHE_MAX_BYTES, BOOKMARK_CACHE_VERSION
            )
            val data = cache.lookup(uri.hashCode().toLong())
            if (data == null) return null
            val dataInputStream = DataInputStream(ByteArrayInputStream(data))
            val uriString = DataInputStream.readUTF(dataInputStream)
            if (!uriString.equals(uri.toString())) return null
            val bookmark = dataInputStream.readInt()
            val duration = dataInputStream.readInt()
            if ((bookmark < HALF_MINUTE) || (duration < TWO_MINUTES)
                || (bookmark > (duration - HALF_MINUTE))
            ) {
                return null;
            }
            return bookmark
        } catch (t: Throwable) {
            Log.e(TAG, "getBookmark failed", t)
        }
        return null
    }

    companion object {
        private const val TAG = "Bookmarker"
        private const val BOOKMARK_CACHE_FILE = "bookmark"
        private const val BOOKMARK_CACHE_MAX_ENTRIES = 100
        private const val BOOKMARK_CACHE_MAX_BYTES = 10 * 1024
        private const val BOOKMARK_CACHE_VERSION = 1
        private const val HALF_MINUTE = 30 * 1000
        private const val TWO_MINUTES = 4 * HALF_MINUTE
    }
}