package euphoria.psycho.explorer

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.*
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import euphoria.psycho.common.*
import euphoria.psycho.common.ui.SwipeRefreshLayout
import euphoria.psycho.player.VideoPlayer
import euphoria.psycho.videos.R
import euphoria.psycho.videos.databinding.FragmentBookmarkBinding
import kotlinx.android.synthetic.main.fragment_explorer.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.text.Collator
import java.util.*


class ExplorerFragment : Fragment() {
    private lateinit var mExplorerAdapter: ExplorerAdapter
    private lateinit var mDirectory: String
    private lateinit var mRequestManager: RequestManager
    private var mSortBy = SORTBY_DEFAULT
    private var mAscending = true

    private fun fetchItems(
        dir: File,
        sortby: Int = SORTBY_DEFAULT,
        ascending: Boolean = true
    ): MutableList<ExplorerItem> {
        val files = dir.listFiles()
        val list = mutableListOf<ExplorerItem>()
        if (files == null) return list
        val collator = Collator.getInstance(Locale.CHINA)
        if (ascending) {
            when (sortby) {
                SORTBY_NAME -> {
                    files.sortWith(compareBy<File> { it.isFile }.thenBy(collator) { it.name.toLowerCase() })
                }
                SORTBY_DATE -> {
                    files.sortWith(compareBy<File> { it.isFile }.thenBy { it.lastModified() })
                }
                SORTBY_SIZE -> {
                    files.sortWith(compareBy<File> { it.isFile }.thenBy { it.length() })
                }
                SORTBY_TYPE -> {
                    files.sortWith(compareBy<File> { it.isFile }.thenBy { it.name.substringAfterLast('.') })
                }
                SORTBY_DEFAULT -> {
                }
                else -> {
                }
            }
        } else {
            when (sortby) {
                SORTBY_NAME -> {
                    files.sortWith(compareByDescending<File> { it.isFile }.thenByDescending(collator) { it.name.toLowerCase() })
                }
                SORTBY_DATE -> {
                    files.sortWith(compareByDescending<File> { it.isFile }.thenByDescending { it.lastModified() })
                }
                SORTBY_SIZE -> {
                    files.sortWith(compareByDescending<File> { it.isFile }.thenByDescending { it.length() })
                }
                SORTBY_TYPE -> {
                    files.sortWith(compareByDescending<File> { it.isFile }.thenByDescending {
                        it.name.substringAfterLast(
                            '.'
                        )
                    })
                }
                SORTBY_DEFAULT -> {
                }
                else -> {
                }
            }
        }
        files.forEach {
            list.add(
                ExplorerItem(
                    it.absolutePath,
                    it.isFile,
                    if (it.isFile) it.length() else (it.listFiles()?.size ?: 0).toLong()
                )
            )
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = dir.name
        return list
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.explorer)
        val preferences = requireContext().getDefaultSharedPreferences()
        mDirectory = preferences.getString(KEY_DIRECTORY, Environment.getExternalStorageDirectory().absolutePath)
        mSortBy = preferences.getInt(KEY_SORTBY, SORTBY_DEFAULT)
        mAscending = preferences.getBoolean(KEY_ASCENDING, true)
        val items = fetchItems(File(mDirectory), mSortBy, mAscending)
        mRequestManager = Glide.with(this)
        mExplorerAdapter = ExplorerAdapter(requireContext(), mRequestManager, items)
        mExplorerAdapter.actionCallback = object : ExplorerActionCallback {
            override fun onCalculateDirectory(item: ExplorerItem) {
                val dir = File(item.fullName)
                if (dir.isDirectory) {
                    GlobalScope.launch {
                        var count = 0
                        var size = 0L
                        dir.walkTopDown().forEach {
                            if (it.isFile) {
                                count++
                                size += it.length()
                            }
                        }
                        GlobalScope.launch(Dispatchers.Main) {
                            Toast.makeText(requireContext(), "文件数：$count,总大小: ${size.formatSize()}", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }

            override fun onCopyFullPath(item: ExplorerItem) {
                item.fullName.copyToClipboard(requireContext())
            }

            override fun onMoveFile(item: ExplorerItem) {
                val editText = EditText(requireContext()).apply {
                    setText(item.fullName.substringAfterLast('/'))
                    val position = item.fullName.getFilenameFromPath().indexOfLast { it == '.' }
                    if (position > 0) {
                        setSelection(0, position)
                    }
                }
                val dialog = AlertDialog
                    .Builder(requireContext())
                    .setTitle(R.string.dialog_title_ask)
                    .setMessage(R.string.dialog_message_move)
                    .setView(editText)
                    .setPositiveButton(android.R.string.ok) { dialog, which ->
                        File(item.fullName).renameTo(File(item.fullName.getParentPath(), editText.text.toString()))
                        refreshList()
                        dialog.dismiss()
                    }.setNegativeButton(android.R.string.cancel) { dialog, which ->
                        dialog.dismiss()
                    }.show()
                dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
            }

            override fun onAddBookmark(item: ExplorerItem) {
                BookmarkDatabase.newInstance(requireContext()).addBookmark(item.fullName)
            }

            override fun onDelete(item: ExplorerItem) {
                AlertDialog
                    .Builder(requireContext())
                    .setTitle(R.string.dialog_title_ask)
                    .setMessage(
                        String.format(
                            getString(R.string.dialog_message_delete),
                            item.fullName.getFilenameFromPath()
                        )
                    )
                    .setPositiveButton(android.R.string.ok) { dialog, which ->
                        deleteFile(item.fullName) {
                            refreshList()
                            dialog.dismiss()
                        }
                    }.setNegativeButton(android.R.string.cancel) { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
        mExplorerAdapter.onClicked = { item ->
            if (item.isFile) {
                if (item.fullName.isVideoFast()) {
                    val intent = Intent(requireContext(), VideoPlayer::class.java)
                    intent.data = Uri.fromFile(File(item.fullName))
                    intent.putExtra(Intent.EXTRA_TITLE, item.fullName.substringAfterLast('/'))
                    requireContext().startActivity(intent)
                } else {
                    requireActivity().openPathIntent(item.fullName)
                }
            } else {
                mDirectory = item.fullName
                refreshList()
            }
        }
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL))
            adapter = mExplorerAdapter
        }
        swipe_layout.listener = object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                mExplorerAdapter.updateDataset(fetchItems(File(mDirectory), mSortBy, mAscending))
                swipe_layout.setRefreshing(false)
            }
        }
    }

    private fun deleteFile(fullPath: String, callback: () -> Unit) {

        val dir = File(fullPath)
        if (dir.isFile) {
            dir.delete()
            callback.invoke()
        } else if (dir.isDirectory) {
            GlobalScope.launch {
                dir.walkBottomUp().forEach { it.delete() }
                GlobalScope.launch(Dispatchers.Main) {
                    requireContext().toast("$fullPath", false)
                    callback.invoke()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_explorer, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_storage -> {
                openBookMark()
                return true
            }
            R.id.action_ascending -> mAscending = true
            R.id.action_descending -> mAscending = false
            R.id.action_sortby_name -> mSortBy = SORTBY_NAME
            R.id.action_sortby_size -> mSortBy = SORTBY_SIZE
            R.id.action_sortby_date -> mSortBy = SORTBY_DATE
            R.id.action_sortby_type -> mSortBy = SORTBY_TYPE
            else -> return true
        }
        refreshList()
        return true
    }

    override fun onPause() {
        super.onPause()
        requireContext().getDefaultSharedPreferences().edit().putString(KEY_DIRECTORY, mDirectory)
            .putInt(KEY_SORTBY, mSortBy)
            .putBoolean(KEY_ASCENDING, mAscending).apply()
    }

    override fun onResume() {
        super.onResume()
        view?.let {
            it.isFocusableInTouchMode = true
            it.requestFocus()
            it.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    val dir = File(mDirectory)
                    return@OnKeyListener dir.parentFile?.let {
                        mDirectory = it.absolutePath
                        mExplorerAdapter.updateDataset(fetchItems(it, mSortBy, mAscending))
                        true
                    } ?: false
                } else false
            })
        }
    }

    private fun openBookMark() {
        val context = requireContext()
        val bookmarkAdapter = BookmarkAdapter(BookmarkDatabase.newInstance(requireContext()).fetchBookmarks())
        val binding =
            DataBindingUtil.inflate<FragmentBookmarkBinding>(
                LayoutInflater.from(context),
                R.layout.fragment_bookmark,
                null,
                false
            )
        binding.recyclerView.run {
            setHasFixedSize(true)
            adapter = bookmarkAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        val point = requireActivity().getRealSize()
        val popupWindow = PopupWindow(
            binding.root,
            (point.x * 0.88).toInt(),
            WindowManager.LayoutParams.WRAP_CONTENT
        ).apply {
            isOutsideTouchable = true
            setBackgroundDrawable(ColorDrawable(Color.WHITE))
            showAtLocation(swipe_layout, Gravity.CENTER, 0, 0)
        }
        bookmarkAdapter.bookmarkClickCallback = object : BookmarkClickCallback {
            override fun onClick(bookmark: Bookmark) {
                mDirectory = bookmark.fullPath
                refreshList()
                popupWindow.dismiss()
            }
        }
    }

    private fun refreshList() {
        mExplorerAdapter.updateDataset(fetchItems(File(mDirectory), mSortBy, mAscending))
    }

    companion object {

        private const val KEY_DIRECTORY = "directory"
        private const val KEY_SORTBY = "sortby"
        private const val KEY_ASCENDING = "ascending"
        private const val SORTBY_DEFAULT = 0
        private const val SORTBY_NAME = 1 shl 1
        private const val SORTBY_SIZE = 1 shl 2
        private const val SORTBY_DATE = 1 shl 3
        private const val SORTBY_TYPE = 1 shl 4

    }
}

