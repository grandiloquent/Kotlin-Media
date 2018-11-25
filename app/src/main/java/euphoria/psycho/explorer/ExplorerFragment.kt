package euphoria.psycho.explorer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import euphoria.psycho.common.getDefaultSharedPreferences
import euphoria.psycho.common.isVideoFast
import euphoria.psycho.common.ui.SwipeRefreshLayout
import euphoria.psycho.videos.MovieActivity
import euphoria.psycho.videos.MoviePlayer
import euphoria.psycho.videos.R
import kotlinx.android.synthetic.main.abc_alert_dialog_material.*
import kotlinx.android.synthetic.main.fragment_explorer.*
import java.io.File
import java.text.Collator
import java.util.*
import android.view.KeyEvent.KEYCODE_BACK


class ExplorerFragment : Fragment() {
    private lateinit var mExplorerAdapter: ExplorerAdapter
    private lateinit var mDirectory: String
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRequestManager: RequestManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    private fun fetchItems(dir: File, sortby: Int = 0, ascending: Boolean = true): MutableList<ExplorerItem> {
        val files = dir.listFiles()
        val list = mutableListOf<ExplorerItem>()

        if (files == null) return list

        val collator = Collator.getInstance(Locale.CHINA)

        files.sortWith(compareBy<File> { it.isFile }.thenBy(collator) { it.name.toLowerCase() })
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
        val preferences = requireContext().getDefaultSharedPreferences()
        mDirectory = preferences.getString(KEY_DIRECTORY, Environment.getExternalStorageDirectory().absolutePath)
        val items = fetchItems(File(mDirectory))
        mRequestManager = Glide.with(this)
        mExplorerAdapter = ExplorerAdapter(requireContext(), mRequestManager, items)
        mExplorerAdapter.onClicked = { item ->
            if (item.isFile) {
                if (item.fullName.isVideoFast()) {
                    val intent = Intent(requireContext(), MovieActivity::class.java)
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

    override fun onPause() {
        super.onPause()
        requireContext().getDefaultSharedPreferences().edit().putString(KEY_DIRECTORY, mDirectory).apply()
    }

    companion object {

        private const val KEY_DIRECTORY = "directory"
    }
}