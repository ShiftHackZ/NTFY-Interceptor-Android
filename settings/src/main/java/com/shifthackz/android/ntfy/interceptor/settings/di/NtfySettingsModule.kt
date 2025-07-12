package com.shifthackz.android.ntfy.interceptor.settings.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.shifthackz.android.ntfy.interceptor.settings.repository.SettingsRepository
import com.shifthackz.android.ntfy.interceptor.settings.repository.SettingsRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val SETTINGS_DATASTORE_NAME = "ntfy_settings_data_store"

private val Context.settingsDataStore by preferencesDataStore(name = SETTINGS_DATASTORE_NAME)

val ntfySettingsModule = module {

    factory<SettingsRepository> {
        SettingsRepositoryImpl(androidContext().settingsDataStore)
    }
}
