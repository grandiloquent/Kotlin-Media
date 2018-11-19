package euphoria.psycho.media


interface Selectable<T> {
    val selectedItemCount: Int
    fun isSelected(item: T): Boolean
    fun toggleSelection(item: T)
    fun clearSelection()
}