package euphoria.psycho.explorer

interface ExplorerActionCallback {
    fun onDelete(item: ExplorerItem)
    fun onCopyFullPath(item: ExplorerItem)
    fun onMoveFile(item: ExplorerItem)
    fun onAddBookmark(item: ExplorerItem)
    fun onCalculateDirectory(item: ExplorerItem)
}