package com.shifthackz.android.ntfy.interceptor

import android.app.Application
import com.shifthackz.android.ntfy.interceptor.di.ntfyAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NtfyInterceptorApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NtfyInterceptorApplication)
            modules(ntfyAppModule)
        }
    }
}
