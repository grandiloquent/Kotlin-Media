package euphoria.psycho.media

import androidx.recyclerview.widget.RecyclerView

abstract class SelectableAdapter<VH : RecyclerView.ViewHolder>(
    items: List<BrowserItem>,
    selectedPaths: List<String>
) :
    RecyclerView.Adapter<VH>(), Selectable<BrowserItem> {
    var items: List<BrowserItem>
        private set
    override val selectedItemCount: Int
        get() = selectedPhotos.size
    protected var selectedPhotos: MutableList<BrowserItem>
    val selectedPaths: ArrayList<String>
        get() = ArrayList<String>().apply {
            selectedPhotos.indices.forEach { this.add((selectedPhotos[it].path)) }
        }


    init {
        this.items = items

        selectedPhotos = ArrayList()
        addPathsToSelections(selectedPaths)

    }

    private fun addPathsToSelections(selectedPaths: List<String>?) {
        selectedPaths?.let {
            for (index in items.indices) {
                for (j in selectedPaths.indices) {
                    if (items[index].path == selectedPaths[j]) {
                        selectedPhotos.add(items[index])
                    }
                }
            }
        }
    }

    override fun isSelected(item: BrowserItem): Boolean {
        return selectedPhotos.contains(item)
    }

    override fun toggleSelection(item: BrowserItem) {
        if (selectedPhotos.contains(item))
            selectedPhotos.remove(item)
        else
            selectedPhotos.add(item)
    }

    override fun clearSelection() {
        selectedPhotos.clear()
        notifyDataSetChanged()
    }

    fun selectAll() {
        selectedPhotos.apply {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }

    fun setData(items: List<BrowserItem>) {
        this.items = items
    }
}