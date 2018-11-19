package euphoria.psycho.media

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView = itemView.findViewById<ImageView>(R.id.iv_photo)
    val title = itemView.findViewById<TextView>(R.id.folder_title)
    val count = itemView.findViewById<TextView>(R.id.folder_count)
    val bottomOverlay = itemView.findViewById<View>(R.id.bottomOverlay)
    val selectedBackground = itemView.findViewById<View>(R.id.transparent_bg)
}