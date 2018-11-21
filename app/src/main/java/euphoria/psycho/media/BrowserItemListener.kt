package euphoria.psycho.media

interface BrowserItemListener {
    fun onClicked(browserItem: BrowserItem)
    fun onCameraClicked()
    fun onLongClicked(browserItem: BrowserItem): Boolean
}