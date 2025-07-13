package com.shifthackz.android.ntfy.interceptor.settings.model

import androidx.datastore.preferences.core.stringPreferencesKey

internal object SettingsKey {
    val BaseUrl = stringPreferencesKey("ntfy_base_url")
    val Username = stringPreferencesKey("ntfy_username")
    val Password = stringPreferencesKey("ntfy_password")
    val Topic = stringPreferencesKey("ntfy_topic")
    val AppOverrides = stringPreferencesKey("ntfy_app_overrides")
}
