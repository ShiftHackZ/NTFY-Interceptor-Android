package com.shifthackz.android.ntfy.interceptor.settings.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.shifthackz.android.ntfy.interceptor.settings.model.NtfyPreferences
import com.shifthackz.android.ntfy.interceptor.settings.model.SettingsKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class SettingsRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : SettingsRepository {

    override fun observe(): Flow<NtfyPreferences> = dataStore.data.map {
        NtfyPreferences(
            baseUrl = it[SettingsKey.BaseUrl] ?: "",
            username = it[SettingsKey.Username] ?: "",
            password = it[SettingsKey.Password] ?: "",
            topic = it[SettingsKey.Topic] ?: ""
        )
    }

    override fun getBaseUrl(): Flow<String> = observe().map { it.baseUrl }

    override fun getTopic(): Flow<String> = observe().map { it.topic }

    override suspend fun saveBaseUrl(value: String): Result<Unit> = runCatching {
        dataStore.edit { it[SettingsKey.BaseUrl] = value }
    }

    override suspend fun saveUsername(value: String): Result<Unit> = runCatching {
        dataStore.edit { it[SettingsKey.Username] = value }
    }

    override suspend fun savePassword(value: String): Result<Unit> = runCatching {
        dataStore.edit { it[SettingsKey.Password] = value }
    }

    override suspend fun saveTopic(value: String): Result<Unit> = runCatching {
        dataStore.edit { it[SettingsKey.Topic] = value }
    }
}
