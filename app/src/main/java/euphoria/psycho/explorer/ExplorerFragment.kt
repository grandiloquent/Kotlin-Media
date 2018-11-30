package euphoria.psycho.explorer

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.*
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import euphoria.psycho.common.getDefaultSharedPreferences
import euphoria.psycho.common.getRealSize
import euphoria.psycho.common.isVideoFast
import euphoria.psycho.common.ui.SwipeRefreshLayout
import euphoria.psycho.player.VideoPlayer
import euphoria.psycho.videos.R
import euphoria.psycho.videos.databinding.FragmentBookmarkBinding
import kotlinx.android.synthetic.main.fragment_explorer.*
import java.io.File
import java.text.Collator
import java.util.*


class ExplorerFragment : Fragment() {
    private lateinit var mExplorerAdapter: ExplorerAdapter
    private lateinit var mDirectory: String
    private lateinit var mRequestManager: RequestManager
    private var mSortBy = SORTBY_DEFAULT
    private var mAscending = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

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
        mExplorerAdapter.onClicked = { item ->
            if (item.isFile) {
                if (item.fullName.isVideoFast()) {
                    val intent = Intent(requireContext(), VideoPlayer::class.java)
                    intent.data = Uri.fromFile(File(item.fullName))
                    intent.putExtra(Intent.EXTRA_TITLE, item.fullName.substringAfterLast('/'))
                    requireContext().startActivity(intent)
                } else {
//                    requireContext().startActivity(Intent(Intent.ACTION_VIEW).also {
//                        it.data = Uri.fromFile(File(item.fullName))
//                    })
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_explorer, container, false)
    }

    private fun refreshList() {
        mExplorerAdapter.updateDataset(fetchItems(File(mDirectory), mSortBy, mAscending))
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

