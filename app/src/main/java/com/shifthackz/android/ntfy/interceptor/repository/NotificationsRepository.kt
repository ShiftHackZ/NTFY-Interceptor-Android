package com.shifthackz.android.ntfy.interceptor.repository

import com.shifthackz.android.ntfy.interceptor.api.NtfyApi
import com.shifthackz.android.ntfy.interceptor.common.model.PushNotification
import com.shifthackz.android.ntfy.interceptor.core.randomness.SecureRandomCharMapNoiseGenerator
import com.shifthackz.android.ntfy.interceptor.database.NtfyDatabase
import com.shifthackz.android.ntfy.interceptor.model.Priority
import com.shifthackz.android.ntfy.interceptor.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class NotificationsRepository(
    private val ntfyApi: NtfyApi,
    private val ntfyNotificationDb: NtfyDatabase.Notification,
    private val secureRandomCharMapNoiseGenerator: SecureRandomCharMapNoiseGenerator,
    private val settingsRepository: SettingsRepository,
) {

    fun observe(): Flow<List<PushNotification>> = ntfyNotificationDb.observeAll()

    suspend fun publishNotification(notification: PushNotification) {
        if (notification.isEmpty) {
            Timber.w("Notification is empty, so it won't be published, skipping.")
            return
        }
        if (!settingsRepository.isAppEnabled(notification.packageName)) {
            Timber.w("App is disabled in interceptor settings. Package name is: ${notification.packageName}")
            return
        }
        ntfyNotificationDb.insert(notification)
        ntfyApi.postNotification(
            packageName = notification.packageName,
            title = "[${notification.packageName}] ${notification.title}".obfuscate(),
            message = notification.body.obfuscate(),
            priority = Priority.High,
        )
    }

    private fun String.obfuscate(): String = secureRandomCharMapNoiseGenerator.obfuscate(this)
}
