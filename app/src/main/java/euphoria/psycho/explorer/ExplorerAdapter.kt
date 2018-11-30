package euphoria.psycho.explorer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import euphoria.psycho.common.*
import euphoria.psycho.videos.R
import java.io.File

class ExplorerAdapter(
    private val context: Context,
    private val glide: RequestManager,
    private val dataset: MutableList<ExplorerItem>
) :
    RecyclerView.Adapter<ExplorerAdapter.ViewHolder>() {
    var actionCallback: ExplorerActionCallback? = null
    val mOptions by lazy {
        val imageSize = context.dp2px(40.0f).toInt()
        RequestOptions()
            .centerCrop()
            .override(imageSize, imageSize)
            .placeholder(R.drawable.file_other)
    }
    val mFolderIcon by lazy {
        context.resources.getDrawable(R.drawable.ic_folder)
    }
    val mZipIcon by lazy {
        context.resources.getDrawable(R.drawable.file_zip)
    }
    val mTxtIcon by lazy {
        context.resources.getDrawable(R.drawable.file_txt)
    }
    val mAudioIcon by lazy {
        context.resources.getDrawable(R.drawable.file_audio)
    }
    val mOtherIcon by lazy {
        context.resources.getDrawable(R.drawable.file_other)
    }

    var onClicked: ((ExplorerItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_explorer, parent, false)
        return ViewHolder(view)
    }

    fun updateDataset(list: MutableList<ExplorerItem>) {
        dataset.clear()
        dataset.addAll(list)
        notifyDataSetChanged()
    }

    private fun showMenu(viewHolder: ViewHolder) {
        PopupMenu(context, viewHolder.image_more).run {
            menuInflater.inflate(R.menu.menu_file, menu)
            setOnMenuItemClickListener { item ->
                actionCallback?.let {
                    val set = dataset[viewHolder.adapterPosition]
                    when (item.itemId) {
                        R.id.action_delete -> it.onDelete(set)
                        R.id.action_rename -> it.onMoveFile(set)
                        R.id.action_copy_full_path -> it.onCopyFullPath(set)
                        R.id.action_add_bookmark -> it.onAddBookmark(set)
                        R.id.action_calculate_directory -> it.onCalculateDirectory(set)
                    }
                }

                true
            }
            show()
        }

    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataset.get(position)
        if (item.isFile) {

            if (item.fullName.isPhotoFast() || item.fullName.isVideoFast()) {
                glide.load(File(item.fullName))
                    .apply(mOptions)
                    .thumbnail(0.5f)
                    .into(holder.imageView)
            } else if (item.fullName.isAudioFast()) {
                holder.imageView.setImageDrawable(mAudioIcon)
            } else if (item.fullName.isAudioFast()) {
                holder.imageView.setImageDrawable(mZipIcon)
            } else if (item.fullName.isPlainTextFast()) {
                holder.imageView.setImageDrawable(mTxtIcon)
            } else {
                holder.imageView.setImageDrawable(mOtherIcon)
            }
        } else {
            holder.imageView.setImageDrawable(mFolderIcon)
        }
        onClicked?.let { callback ->
            holder.itemView.setOnClickListener { callback.invoke(item) }
        }
        holder.textViewName.text = item.fullName.substringAfterLast('/')
        holder.textViewSize.text = if (item.isFile) item.size.formatSize() else "${item.size} 个"
        holder.image_more.setOnClickListener { showMenu(holder) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val textViewName = itemView.findViewById<TextView>(R.id.textViewName)
        val textViewSize = itemView.findViewById<TextView>(R.id.textViewSize)
        val image_more = itemView.findViewById<ImageView>(R.id.image_more)
    }
}

