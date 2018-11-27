package euphoria.psycho.explorer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import euphoria.psycho.common.getDefaultSharedPreferences
import euphoria.psycho.common.isVideoFast
import euphoria.psycho.common.ui.SwipeRefreshLayout
import euphoria.psycho.player.VideoPlayer
import euphoria.psycho.videos.MovieActivity
import euphoria.psycho.videos.MoviePlayer
import euphoria.psycho.videos.R
import kotlinx.android.synthetic.main.abc_alert_dialog_material.*
import kotlinx.android.synthetic.main.fragment_explorer.*
import java.io.File
import java.text.Collator
import java.util.*


class ExplorerFragment : Fragment() {
    private lateinit var mExplorerAdapter: ExplorerAdapter
    private lateinit var mDirectory: String
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRequestManager: RequestManager
    private var mSortBy = SORTBY_DEFAULT
    private var mAscending = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

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
        return list
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_explorer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.explorer)
        val preferences = requireContext().getDefaultSharedPreferences()
        mDirectory = preferences.getString(KEY_DIRECTORY, Environment.getExternalStorageDirectory().absolutePath)
        mSortBy = preferences.getInt(KEY_SORTBY, SORTBY_DEFAULT)
        mAscending = preferences.getBoolean(KEY_ASCENDING, true)
        val items = fetchItems(File(mDirectory))
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
                mExplorerAdapter.updateDataset(fetchItems(File(mDirectory)))
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = mExplorerAdapter

        swipeRefreshLayout.listener = object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                mExplorerAdapter.updateDataset(fetchItems(File(mDirectory)))
                swipeRefreshLayout.setRefreshing(false)
            }

        }

    }

    override fun onResume() {
        super.onResume()


        view?.let {
            it.isFocusableInTouchMode = true
            it.requestFocus()
            it.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {

                    return if (event.getAction() === KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                        val dir = File(mDirectory)
                        return dir.parentFile?.let {
                            mExplorerAdapter.updateDataset(fetchItems(it))
                            true
                        } ?: false

                    } else false
                }
            })
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_storage -> {

                mDirectory = Environment.getExternalStorageDirectory().absolutePath
            }
            R.id.action_ascending -> mAscending = true
            R.id.action_descending -> mAscending = false
            R.id.action_sortby_name -> mSortBy = SORTBY_NAME
            R.id.action_sortby_size -> mSortBy = SORTBY_SIZE
            R.id.action_sortby_date -> mSortBy = SORTBY_DATE
            R.id.action_sortby_type -> mSortBy = SORTBY_TYPE

            else -> return true
        }
        mExplorerAdapter.updateDataset(fetchItems(File(mDirectory), mSortBy, mAscending))
        return true
    }

    override fun onPause() {
        super.onPause()
        requireContext().getDefaultSharedPreferences().edit().putString(KEY_DIRECTORY, mDirectory)
            .putInt(KEY_SORTBY, mSortBy)
            .putBoolean(KEY_ASCENDING, mAscending).apply()
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