package com.shifthackz.android.ntfy.interceptor

import android.app.Application
import com.shifthackz.android.ntfy.interceptor.database.tree.RoomLoggingTree
import com.shifthackz.android.ntfy.interceptor.di.ntfyAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class NtfyInterceptorApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NtfyInterceptorApplication)
            modules(ntfyAppModule)
        }
        Timber.plant(RoomLoggingTree())
        Timber.plant(Timber.DebugTree())
    }
}
