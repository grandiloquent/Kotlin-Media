package euphoria.psycho.explorer

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import euphoria.psycho.common.getDefaultSharedPreferences
import euphoria.psycho.videos.R
import kotlinx.android.synthetic.main.fragment_explorer.*
import java.io.File
import java.text.Collator
import java.util.*

class ExplorerFragment : Fragment() {
    private lateinit var mExplorerAdapter: ExplorerAdapter
    private lateinit var mDirectory: String
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    private fun fetchItems(dir: File): MutableList<ExplorerItem> {
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
        mExplorerAdapter = ExplorerAdapter(requireContext(), items)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = mExplorerAdapter
    }

    companion object {

        private const val KEY_DIRECTORY = "directory"
    }
}