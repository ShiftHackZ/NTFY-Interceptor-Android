package com.shifthackz.android.ntfy.interceptor.settings.repository

import com.shifthackz.android.ntfy.interceptor.settings.model.NtfyPreferences
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    fun observe(): Flow<NtfyPreferences>

    fun getBaseUrl(): Flow<String>

    suspend fun saveBaseUrl(value: String): Result<Unit>

    suspend fun saveUsername(value: String): Result<Unit>

    suspend fun savePassword(value: String): Result<Unit>
}
