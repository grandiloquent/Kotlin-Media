package euphoria.psycho.explorer

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Environment
import java.io.File

class BookmarkDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun fetchBookmarks(): MutableList<Bookmark> {
        return mutableListOf<Bookmark>().apply {
            add(Bookmark(Environment.getExternalStorageDirectory().absolutePath, "Storage"))
        }

    }

    companion object {
        fun newInstance(context: Context): BookmarkDatabase {
            return BookmarkDatabase(context)
        }

        const val DATABASE_VERSION = 1
        val DATABASE_NAME: String by lazy {
            File(Environment.getExternalStorageDirectory(), "file_bookmark.db").absolutePath
        }
    }
}