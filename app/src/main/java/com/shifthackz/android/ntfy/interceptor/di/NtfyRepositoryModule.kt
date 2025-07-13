package com.shifthackz.android.ntfy.interceptor.di

import com.shifthackz.android.ntfy.interceptor.repository.LogRepository
import com.shifthackz.android.ntfy.interceptor.repository.NotificationsRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val ntfyRepositoryModule = module {
    factoryOf(::NotificationsRepository)
    factoryOf(::LogRepository)
}
