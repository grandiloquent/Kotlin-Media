package euphoria.psycho.media

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import euphoria.psycho.videos.PlayerActivity
import java.io.File
import kotlin.math.abs

class MediaFragment : Fragment(), BrowserItemListener {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mEmptyView: View
    private lateinit var mRequestManager: RequestManager
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private var mBrowserAdapter: BrowserAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_media_folder_picker, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    private fun getList(dir: File): MutableList<BrowserItem> {
        val mutableList: MutableList<BrowserItem> = arrayListOf()

        dir.listFiles()?.let {
            it.filter {
                Regex(
                    "\\.(?:bmp|jpeg|jpg|png|webp" +
                            "|3gp|3gpp|avi|m4v|mkv|mov|mp4|webm" +
                            "|aac|flac|m4a|mp3|ogg|opus|wav|wma" +
                            ")$"
                ).containsMatchIn(it.name)
            }
                .forEach { mutableList.add(BrowserItem(it.absolutePath)) }
        }
        return mutableList
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mEmptyView = view.findViewById(R.id.empty_view)
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.apply {
            setDistanceToTriggerSync(DISTANCE_TO_TRIGGER)
            setOnRefreshListener {
                val list = getList("downloads".getExternalStorageFile())
                mBrowserAdapter?.updateData(list)
                isRefreshing = false
            }
        }
        setupRecyclerView(view)


        mRequestManager = Glide.with(this)
        val list = getList("downloads".getExternalStorageFile())
        context?.let {
            mBrowserAdapter = BrowserAdapter(it, mRequestManager, list, ArrayList(), true).also {
                it.browserItemListener = this
            }
            mRecyclerView.adapter = mBrowserAdapter
        }


    }

    override fun onClicked(browserItem: BrowserItem) {
        val intent = Intent(context, PlayerActivity::class.java)
        intent.data = File(browserItem.path).toUri()
        startActivityForResult(intent, REQUEST_VIDEO_CODE)
    }

    override fun onCameraClicked() {
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

    companion object {
        private val SCROLL_THRESHOLD = 30
        private const val REQUEST_VIDEO_CODE = 1
        private const val DISTANCE_TO_TRIGGER = 400
    }
}