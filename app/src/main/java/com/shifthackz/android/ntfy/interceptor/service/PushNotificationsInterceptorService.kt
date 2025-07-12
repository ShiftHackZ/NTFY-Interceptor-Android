package com.shifthackz.android.ntfy.interceptor.service

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.shifthackz.android.ntfy.interceptor.api.NtfyApi
import com.shifthackz.android.ntfy.interceptor.model.Priority
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class PushNotificationsInterceptorService : NotificationListenerService() {

    private val ntfyApi: NtfyApi by inject()

    private val serviceScope = CoroutineScope(
        SupervisorJob() +
                Dispatchers.IO +
                CoroutineExceptionHandler { _, e -> Log.e("NTFYI", "Error occurred!", e) }
    )

    override fun onCreate() {
        Log.i("NTFYI", "Interceptor service successfully created!")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        if (sbn == null) {
            Log.w("NTFYI", "Status bar notification is null, skipping.")
            return
        }

        val application = sbn.packageName
        val title = sbn.notification.extras.getString(Notification.EXTRA_TITLE) ?: ""
        val body = sbn.notification.extras.getString(Notification.EXTRA_TEXT) ?: ""

        serviceScope.launch {
            ntfyApi.postNotification(
                topic = "test",
                title = "[$application] $title",
                message = body,
                priority = Priority.High
            )
        }
    }

    override fun onDestroy() {
        serviceScope.cancel("PushNotificationsInterceptorService has been destroyed!")
        super.onDestroy()
        Log.i("NTFYI", "Interceptor service has beed destroyed!")
    }
}
