package com.shifthackz.android.ntfy.interceptor.repository

import com.shifthackz.android.ntfy.interceptor.api.NtfyApi
import com.shifthackz.android.ntfy.interceptor.common.model.PushNotification
import com.shifthackz.android.ntfy.interceptor.database.NtfyDatabase
import com.shifthackz.android.ntfy.interceptor.model.Priority
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class NotificationsRepository(
    private val ntfyApi: NtfyApi,
    private val ntfyNotificationDb: NtfyDatabase.Notification,
) {

    fun observe(): Flow<List<PushNotification>> = ntfyNotificationDb.observeAll()

    suspend fun publishNotification(notification: PushNotification) {
        if (notification.isEmpty) {
            Timber.w("Notification is empty, so it won't be published, skipping.")
            return
        }
        ntfyNotificationDb.insert(notification)
        ntfyApi.postNotification(
            title = "[${notification.packageName}] ${notification.title}",
            message = notification.body,
            priority = Priority.High,
        )
    }
}
