package com.shifthackz.android.ntfy.interceptor.settings.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.shifthackz.android.ntfy.interceptor.common.model.InstalledApp
import com.shifthackz.android.ntfy.interceptor.settings.datastore.settingsDataStore
import com.shifthackz.android.ntfy.interceptor.settings.model.AppOverrideSettings
import com.shifthackz.android.ntfy.interceptor.settings.model.NtfyPreferences
import com.shifthackz.android.ntfy.interceptor.settings.model.SettingsKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

internal class SettingsRepositoryImpl(
    private val appContext: Context,
) : SettingsRepository {

    private val dataStore: DataStore<Preferences> = appContext.settingsDataStore

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = false
        isLenient = true
        encodeDefaults = true
    }

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

    override fun observeInstalledApps(): Flow<List<InstalledApp>> = flow {
        val pm = appContext.packageManager
        val apps = pm.getInstalledApplications(0)

        val installedApps = apps.map {
            val label = it.loadLabel(pm).toString()
            val packageName = it.packageName
            val icon = it.loadIcon(pm)
            Triple(label, packageName, icon)
        }

        val overridesJson = dataStore.data.map { it[SettingsKey.AppOverrides] ?: "{}" }.first()
        val overrides: Map<String, AppOverrideSettings> = runCatching {
            json.decodeFromString<Map<String, AppOverrideSettings>>(overridesJson)
        }.getOrElse { emptyMap() }

        val defaultTopic = dataStore.data.map { it[SettingsKey.Topic] ?: "" }.first()

        val result = installedApps.map { (label, pkg, icon) ->
            val override = overrides[pkg]
            val isEnabled = override?.isEnabled ?: true

            InstalledApp(
                appName = label,
                packageName = pkg,
                icon = icon,
                isEnabled = isEnabled,
                topic = "${defaultTopic}-${pkg.replace(".", "-")}"
            )
        }.sortedBy { it.appName.lowercase() }

        emit(result)
    }

    override suspend fun updateAppOverride(packageName: String, isEnabled: Boolean) {
        dataStore.edit { prefs ->
            val currentJson = prefs[SettingsKey.AppOverrides] ?: "{}"
            val currentMap = runCatching {
                json.decodeFromString<Map<String, AppOverrideSettings>>(currentJson)
            }.getOrElse { emptyMap() }

            val currentOverride = currentMap[packageName]
            val newOverride = AppOverrideSettings(
                isEnabled = isEnabled,
                customTopic = currentOverride?.customTopic.orEmpty()
            )

            val updatedMap = if (isEnabled && newOverride.customTopic.isBlank()) {
                currentMap - packageName
            } else {
                currentMap + (packageName to newOverride)
            }

            prefs[SettingsKey.AppOverrides] = json.encodeToString(updatedMap)
        }
    }

    override suspend fun isAppEnabled(packageName: String): Boolean {
        val currentJson = dataStore.data.first()[SettingsKey.AppOverrides] ?: "{}"
        val overrides: Map<String, AppOverrideSettings> = runCatching {
            json.decodeFromString<Map<String, AppOverrideSettings>>(currentJson)
        }.getOrElse { emptyMap() }

        val override = overrides[packageName]
        return override?.isEnabled ?: true
    }
}
