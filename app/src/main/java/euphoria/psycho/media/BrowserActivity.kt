package euphoria.psycho.media

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class BrowserActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mEmptyView: View


    private fun initialize(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_main)
        mEmptyView = findViewById(R.id.empty_view)
        setupRecyclerView()
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