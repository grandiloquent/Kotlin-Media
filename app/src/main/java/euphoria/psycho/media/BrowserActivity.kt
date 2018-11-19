package euphoria.psycho.media

import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import java.io.File
import kotlin.math.abs

class BrowserActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mEmptyView: View
    private lateinit var mRequestManager: RequestManager
    private var mBrowserAdapter: BrowserAdapter? = null


    private fun initialize(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_main)
        mEmptyView = findViewById(R.id.empty_view)
        setupRecyclerView()
        mRequestManager = Glide.with(this)
        val list = getList("downloads".getExternalStorageFile())
        mBrowserAdapter = BrowserAdapter(this, mRequestManager, list, ArrayList(), true)
        mRecyclerView.adapter = mBrowserAdapter
    }

    private fun getList(dir: File): MutableList<BrowserItem> {
        val mutableList: MutableList<BrowserItem> = ArrayList()

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

    private fun refreshList() {

    }

    private fun setupRecyclerView() {
        val spanCount = 2
        val spacing = 5
        val includeEdge = false
        mRecyclerView = findViewById(R.id.recyclerview)
        mRecyclerView.apply {
            addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))
            layoutManager = GridLayoutManager(this@BrowserActivity, 2)
            itemAnimator = DefaultItemAnimator()
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                    }
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (abs(dy) > SCROLL_THRESHOLD) {

                    } else {

                    }

                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkStoragePermission(savedInstanceState, { state ->
            initialize(state)
        })
    }

    companion object {
        private val SCROLL_THRESHOLD = 30
    }
}