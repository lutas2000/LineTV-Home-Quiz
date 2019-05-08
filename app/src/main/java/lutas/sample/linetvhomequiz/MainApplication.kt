package lutas.sample.linetvhomequiz

import android.app.Application
import lutas.sample.linetvhomequiz.di.appModule
import lutas.sample.linetvhomequiz.di.databaseModule
import lutas.sample.linetvhomequiz.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(remoteModule + databaseModule + appModule)
        }
    }
}