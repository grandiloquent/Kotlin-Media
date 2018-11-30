package euphoria.psycho.explorer

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Environment
import java.io.File

class BookmarkDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE  IF NOT EXISTS bookmark(" +
                    "  _id INTEGER PRIMARY KEY," +
                    "  full_path TEXT unique," +
                    "   create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
        )
    }

    fun addBookmark(fullPath: String) {
        writableDatabase.insertWithOnConflict(TABLE_NAME, null, ContentValues().apply {
            put(KEY_FULL_PATH, fullPath)
            put(KEY_CREATE_AT, " time('now') ")
        }, SQLiteDatabase.CONFLICT_IGNORE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun fetchBookmarks(): MutableList<Bookmark> {
        return mutableListOf<Bookmark>().apply {
            add(Bookmark(Environment.getExternalStorageDirectory().absolutePath, "Storage"))

            readableDatabase.query(TABLE_NAME, null, null, null, null, null, KEY_FULL_PATH + " ASC", null).let {
                while (it.moveToNext()) {
                    this.add(
                        Bookmark(
                            it.getString(1), it.getString(1).substringAfterLast('/')
                        )
                    )
                }
                it.close()
            }
        }

    }

    companion object {
        private const val TABLE_NAME = "bookmark"
        private const val KEY_FULL_PATH = "full_path"
        private const val KEY_CREATE_AT = "create_at"

        fun newInstance(context: Context): BookmarkDatabase {
            return BookmarkDatabase(context)
        }

        const val DATABASE_VERSION = 1
        val DATABASE_NAME: String by lazy {
            File(Environment.getExternalStorageDirectory(), "file_bookmark.db").absolutePath
        }
    }
}