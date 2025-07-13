package com.shifthackz.android.ntfy.interceptor.settings.di

import com.shifthackz.android.ntfy.interceptor.settings.repository.SettingsRepository
import com.shifthackz.android.ntfy.interceptor.settings.repository.SettingsRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val ntfySettingsModule = module {

    factory<SettingsRepository> {
        SettingsRepositoryImpl(
            appContext = androidContext().applicationContext,
        )
    }
}
