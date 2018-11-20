package euphoria.psycho.media

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import euphoria.psycho.common.Values
import euphoria.psycho.common.ui.SwipeRefreshLayout
import euphoria.psycho.videos.PlayerActivity
import java.io.File
import kotlin.math.abs

class MediaFragment : Fragment(), BrowserItemListener {

    private var mSortId = 0
    private var mSequenceId = 0
    private var mCurrentDirectory: String? = null
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mEmptyView: View
    private lateinit var mRequestManager: RequestManager
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private var mBrowserAdapter: BrowserAdapter? = null

    private fun getList(
        dir: File,
        sortBy: Int = SortDialog.SORT_BY_DEFAULT,
        sequenceId: Int = SortDialog.SORT_ASC
    ): MutableList<BrowserItem> {
        val mutableList: MutableList<BrowserItem> = arrayListOf()
        val files = dir.listFiles()?.let {
            it.filter {
                Regex(
                    "\\.(?:bmp|jpeg|jpg|png|webp" +
                            "|3gp|3gpp|avi|m4v|mkv|mov|mp4|webm" +
                            "|aac|flac|m4a|mp3|ogg|opus|wav|wma" +
                            ")$"
                ).containsMatchIn(it.name)
            }
        } ?: return mutableList
        if (sequenceId == SortDialog.SORT_ASC) {
            when (sortBy) {
                SortDialog.SORT_BY_DEFAULT -> {
                }
                SortDialog.SORT_BY_NAME ->

                    files.sortedWith(compareBy<File> { it.isFile }.thenBy { it.name.toLowerCase() }).forEach {
                        mutableList.add(BrowserItem(it.absolutePath, it.length()))
                    }
                SortDialog.SORT_BY_SIZE ->
                    files.sortedWith(compareBy<File> { it.isFile }.thenBy { it.length() }).forEach {
                        mutableList.add(BrowserItem(it.absolutePath, it.length()))
                    }
                SortDialog.SORT_BY_DATE ->
                    files.sortedWith(compareBy<File> { it.isFile }.thenBy { it.lastModified() }).forEach {
                        mutableList.add(BrowserItem(it.absolutePath, it.length()))
                    }
                else -> {
                }
            }
        } else {
            when (sortBy) {
                SortDialog.SORT_BY_DEFAULT -> {
                }
                SortDialog.SORT_BY_NAME ->

                    files.sortedWith(compareBy<File> { it.isFile }.thenByDescending { it.name.toLowerCase() }).forEach {
                        mutableList.add(BrowserItem(it.absolutePath, it.length()))
                    }
                SortDialog.SORT_BY_SIZE ->
                    files.sortedWith(compareBy<File> { it.isFile }.thenByDescending { it.length() }).forEach {
                        mutableList.add(BrowserItem(it.absolutePath, it.length()))
                    }
                SortDialog.SORT_BY_DATE ->
                    files.sortedWith(compareBy<File> { it.isFile }.thenByDescending { it.lastModified() }).forEach {
                        mutableList.add(BrowserItem(it.absolutePath, it.length()))
                    }
                else -> {
                }
            }
        }


        return mutableList
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCameraClicked() {
    }

    override fun onClicked(browserItem: BrowserItem) {
        val intent = Intent(context, PlayerActivity::class.java)
        intent.data = File(browserItem.path).toUri()
        startActivityForResult(intent, REQUEST_VIDEO_CODE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_media_folder_picker, container, false)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sort -> showSortDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mSortId = Values.defaultPreferences.getInt(KEY_SORT, SortDialog.SORT_BY_DEFAULT)
        mSequenceId = Values.defaultPreferences.getInt(KEY_SEQUENCE, SortDialog.SORT_ASC)
        mCurrentDirectory =
                Values.defaultPreferences.getString(KEY_DIRECTORY, "downloads".getExternalStorageFile().absolutePath)

        mEmptyView = view.findViewById(R.id.empty_view)
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.listener = object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                refreshList(File(mCurrentDirectory), mSortId, mSequenceId)
                mSwipeRefreshLayout.setRefreshing(false)
            }
        }
        setupRecyclerView(view)
        mRequestManager = Glide.with(this)
        val list = getList(File(mCurrentDirectory), mSortId, mSequenceId)
        context?.let {
            mBrowserAdapter = BrowserAdapter(it, mRequestManager, list, ArrayList(), true).also {
                it.browserItemListener = this
            }
            mRecyclerView.adapter = mBrowserAdapter
        }
    }

    private fun resumeRequestsIfNotDestroyed() {
        if (!canLoadImage(this)) return
        mRequestManager.resumeRequests()
    }

    private fun setupRecyclerView(view: View) {
        val spanCount = 2
        val spacing = 5
        val includeEdge = false
        mRecyclerView = view.findViewById(R.id.recyclerview)
        mRecyclerView.apply {
            addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
            layoutManager = GridLayoutManager(context, 2)
            itemAnimator = DefaultItemAnimator()
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        resumeRequestsIfNotDestroyed()
                    }
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (abs(dy) > SCROLL_THRESHOLD) {
                        mRequestManager.pauseAllRequests()
                    } else {
                        resumeRequestsIfNotDestroyed()
                    }
                }
            })
        }
    }

    private fun refreshList(dir: File, sortId: Int, sequenceId: Int) {


        mBrowserAdapter?.updateData(getList(dir, sortId, sequenceId))


    }

    override fun onStart() {
        super.onStart()

    }

    override fun onPause() {
        super.onPause()
        Values.defaultPreferences.edit().putInt(KEY_SORT, mSortId).putInt(KEY_SEQUENCE, mSequenceId).putString(
            KEY_DIRECTORY, mCurrentDirectory
        ).apply()
    }

    private fun showSortDialog() {
        activity?.let {
            val fragmentManager = it.supportFragmentManager
            fragmentManager.findFragmentByTag(SORT_DIALOG_TAG)?.apply {
                fragmentManager.beginTransaction().remove(this).commit()
            }
            SortDialog.newInstance(mSortId, mSequenceId)
                .apply {
                    show(fragmentManager, SORT_DIALOG_TAG)
                    onCheckedChanged = { sortId, sequenceId ->
                        mSortId = sortId
                        mSequenceId = sequenceId
                        refreshList(File(mCurrentDirectory), sortId, sequenceId)
                        Values.defaultPreferences.edit().putInt(KEY_SORT, mSortId).putInt(KEY_SEQUENCE, mSequenceId)
                            .apply()
                    }
                }
        }
    }

    companion object {
        private val SCROLL_THRESHOLD = 30
        private const val REQUEST_VIDEO_CODE = 1
        private const val DISTANCE_TO_TRIGGER = 400
        private const val SORT_DIALOG_TAG = "sortDialogTag"
        private const val KEY_SORT = "sort"
        private const val KEY_SEQUENCE = "sequence"
        private const val KEY_DIRECTORY = "directory"
    }
}