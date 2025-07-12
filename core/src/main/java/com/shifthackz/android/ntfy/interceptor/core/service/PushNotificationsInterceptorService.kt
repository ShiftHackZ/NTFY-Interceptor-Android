package com.shifthackz.android.ntfy.interceptor.core.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.shifthackz.android.ntfy.interceptor.core.PushNotificationsInterceptor
import com.shifthackz.android.ntfy.interceptor.core.model.PushNotification
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.koin.android.ext.android.inject

class PushNotificationsInterceptorService : NotificationListenerService() {

    private val interceptor: PushNotificationsInterceptor by inject()

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

        val pushNotification = PushNotification(sbn)
        Log.i(
            "NTFYI",
            "Delivering push notification to interceptor. Seed: ${pushNotification.hashCode()}"
        )
        interceptor.onNewNotification(serviceScope, pushNotification)
    }

    override fun onDestroy() {
        serviceScope.cancel("PushNotificationsInterceptorService has been destroyed!")
        super.onDestroy()
        Log.i("NTFYI", "Interceptor service has beed destroyed!")
    }
}
