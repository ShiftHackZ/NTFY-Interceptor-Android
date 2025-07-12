package com.shifthackz.android.ntfy.interceptor.di

import com.shifthackz.android.ntfy.interceptor.NtfyInterceptorImpl
import com.shifthackz.android.ntfy.interceptor.core.PushNotificationsInterceptor
import org.koin.dsl.module

val ntfyInterceptorsModule = module {

    single<PushNotificationsInterceptor> {
        NtfyInterceptorImpl(get())
    }
}
