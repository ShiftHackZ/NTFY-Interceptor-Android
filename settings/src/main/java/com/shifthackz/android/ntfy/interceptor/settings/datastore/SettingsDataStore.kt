package com.shifthackz.android.ntfy.interceptor.settings.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

private const val SETTINGS_DATASTORE_NAME = "ntfy_settings_data_store"

internal val Context.settingsDataStore by preferencesDataStore(name = SETTINGS_DATASTORE_NAME)
