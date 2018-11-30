package euphoria.psycho.explorer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import euphoria.psycho.videos.R
import euphoria.psycho.videos.databinding.ItemBookmarkBinding

class BookmarkAdapter(
    private var dataset: MutableList<Bookmark>?

) :
    RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {
    var bookmarkClickCallback: BookmarkClickCallback? = null

    init {
        setHasStableIds(true)
    }
    override fun getItemCount(): Int {
        return dataset?.size ?: 0
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bookmarkBinding.apply {
            item = dataset?.get(position)
            executePendingBindings()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return DataBindingUtil.inflate<ItemBookmarkBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_bookmark,
            parent,
            false
        ).run {
            callback = bookmarkClickCallback
            ViewHolder(this)
        }
    }
    fun setDataset(newDataset: MutableList<Bookmark>) {
        if (this.dataset == null) {
            this.dataset = newDataset
            notifyItemRangeInserted(0, newDataset.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return dataset?.let { it[oldItemPosition].name == newDataset[newItemPosition].name } ?: false
                }
                override fun getOldListSize(): Int {
                    return dataset?.size ?: 0
                }
                override fun getNewListSize(): Int {
                    return newDataset.size
                }
                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return dataset?.let {
                        val newItem = newDataset[newItemPosition]
                        val oldItem = it[oldItemPosition]
                        return newItem.name == oldItem.name && newItem.fullPath == oldItem.fullPath
                    } ?: false
                }
            })
            dataset = newDataset
            result.dispatchUpdatesTo(this)
        }
    }

    class ViewHolder(val bookmarkBinding: ItemBookmarkBinding) : RecyclerView.ViewHolder(bookmarkBinding.root) {

    }
}