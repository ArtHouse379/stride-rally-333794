package teeupgolfleisure.athletics.striderally

import android.app.Application
import teeupgolfleisure.athletics.striderally.di.dataModule
import teeupgolfleisure.athletics.striderally.di.dispatcherModule
import teeupgolfleisure.athletics.striderally.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class SDPMEApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@SDPMEApplication)
            modules(appModules)
        }
    }
}
