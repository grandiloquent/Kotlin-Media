package euphoria.psycho.browser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import euphoria.psycho.media.R
import euphoria.psycho.media.databinding.FileFragmentBinding

class BrowserFragment : Fragment(), FileItemListener, Observer<MutableList<FileItem>> {


    private lateinit var mBinding: FileFragmentBinding
    private var mAdapter: FileAdapter? = null
    private lateinit var mViewModel: FileFragmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAdapter = FileAdapter(this)
        mViewModel = ViewModelProviders.of(requireActivity(), FileFragmentViewModel.Factory(requireContext()))
            .get(FileFragmentViewModel::class.java)
        mViewModel.dataset.observe(this, this)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.file_fragment, container, false)
        mBinding.uiRecyclerView.adapter = mAdapter
        return mBinding.root
    }

    override fun onChanged(t: MutableList<FileItem>?) {
        t?.let {
            mAdapter?.setData(it)
        }

    }

    override fun onClicked(item: FileItem) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mBinding.uiRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mBinding.uiRecyclerView.adapter = mAdapter
        mViewModel.refresh()
    }

}