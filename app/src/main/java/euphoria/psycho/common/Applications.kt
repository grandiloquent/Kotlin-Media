package euphoria.psycho.comm

import android.app.Application
import euphoria.psycho.common.Values

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Values.context = this
    }
}