package euphoria.psycho.media

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import euphoria.psycho.common.formatSize
import java.io.File

class BrowserAdapter(
    private val context: Context,
    private val glide: RequestManager,
    items: MutableList<BrowserItem>,
    selectPaths: List<String>,
    private val showCamera: Boolean
) :
    SelectableAdapter<ViewHolder>(items, selectPaths) {

    var browserItemListener: BrowserItemListener? = null
    private var mImageSize = 0
    private var mRequestOptions: RequestOptions

    init {
        setColumnNumber(3)
        mRequestOptions = RequestOptions()
            .centerCrop()
            .override(mImageSize, mImageSize)
            .placeholder(R.drawable.image_placeholder)

    }

    private fun setColumnNumber(columnNumber: Int) {
        mImageSize = context.displayMetrics().widthPixels / columnNumber
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_folder_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return if (showCamera) items.size + 1 else items.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_TYPE_PHOTO) {
            val item = items[if (showCamera) position - 1 else position]
            if (canLoadImage(holder.imageView.context)) {
                glide.load(File(item.path))
                    .apply(mRequestOptions)
                    .thumbnail(0.5f)
                    .into(holder.imageView)
            }
            holder.title.text = item.path.substringAfterLast('/')
            holder.count.text = item.size.formatSize()
            browserItemListener?.let { callback -> holder.itemView.setOnClickListener { callback.onClicked(item) } }
            holder.bottomOverlay.visibility = View.VISIBLE
        } else {
            holder.imageView.setImageResource(R.drawable.ic_camera)
            holder.bottomOverlay.visibility = View.GONE
            browserItemListener?.let { callback -> holder.itemView.setOnClickListener { callback.onCameraClicked() } }

        }

    }

    fun updateData(list: List<BrowserItem>) {
        setData(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (showCamera && position == 0) ITEM_TYPE_CAMERA else ITEM_TYPE_PHOTO

    }

    companion object {
        val ITEM_TYPE_CAMERA = 1 shl 1
        val ITEM_TYPE_PHOTO = 1 shl 2
    }
}
