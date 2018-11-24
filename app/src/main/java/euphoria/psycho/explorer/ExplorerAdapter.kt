package euphoria.psycho.explorer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import euphoria.psycho.common.formatSize
import euphoria.psycho.videos.R

class ExplorerAdapter(private val context: Context, private val dataset: MutableList<ExplorerItem>) :
    RecyclerView.Adapter<ExplorerAdapter.ViewHolder>() {

    val mFolderIcon by lazy {
        context.resources.getDrawable(R.drawable.ic_folder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_explorer, parent)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataset.get(position)
        if (item.isFile) {

        } else {
            holder.imageView.setImageDrawable(mFolderIcon)
        }
        holder.textViewName.text = item.fullName.substringAfterLast('/')
        holder.textViewSize.text = if (item.isFile) item.size.formatSize() else "${item.size} 个"

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val textViewName = itemView.findViewById<TextView>(R.id.textViewName)
        val textViewSize = itemView.findViewById<TextView>(R.id.textViewSize)
    }
}