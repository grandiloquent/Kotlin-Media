package euphoria.psycho.browser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import euphoria.psycho.media.R
import euphoria.psycho.media.databinding.FileItemBinding

class FileAdapter(val fileItemListener: FileItemListener) : RecyclerView.Adapter<FileAdapter.FileViewHolder>() {
    private var mItems: MutableList<FileItem>? = null

    override fun getItemCount(): Int {
        return mItems?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val binding = DataBindingUtil.inflate<FileItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.file_item,
            parent,
            false
        )
        binding.listener = fileItemListener
        return FileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.fileItemBinding.fileItem = mItems?.get(position)
        holder.fileItemBinding.executePendingBindings()
    }

    fun setData(items: MutableList<FileItem>) {
        mItems?.let {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return mItems?.let {
                        it[oldItemPosition].fullName.equals(items[newItemPosition])
                    } ?: true
                }

                override fun getOldListSize(): Int {
                    return mItems?.size ?: 0
                }

                override fun getNewListSize(): Int {
                    return items.size
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return mItems?.let {
                        it[oldItemPosition].fullName.equals(items[newItemPosition])
                    } ?: true
                }

            })
            mItems = items
            result.dispatchUpdatesTo(this)
        } ?: run {
            this.mItems = items
            notifyItemRangeInserted(0, items.size)
        }
    }

    class FileViewHolder(val fileItemBinding: FileItemBinding) : RecyclerView.ViewHolder(fileItemBinding.root) {

    }
}
