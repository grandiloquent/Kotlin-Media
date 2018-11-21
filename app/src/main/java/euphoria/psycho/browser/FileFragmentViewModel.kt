package euphoria.psycho.browser

import android.app.Application
import android.content.Context
import android.os.Environment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import euphoria.psycho.common.util.LiveDataset

class FileFragmentViewModel(
    context: Context
) :
    ViewModel() {
    val dataset by lazy {

        LiveDataset<FileItem>()
    }


    fun refresh() {
        Environment.getExternalStorageDirectory().listFiles().forEach {
            dataset.add(FileItem(it.absolutePath, it.name, it.length()))
        }
    }

    class Factory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FileFragmentViewModel(context.applicationContext) as T
        }
    }
}