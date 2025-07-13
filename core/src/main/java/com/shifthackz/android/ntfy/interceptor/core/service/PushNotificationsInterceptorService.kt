package com.shifthackz.android.ntfy.interceptor.core.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.shifthackz.android.ntfy.interceptor.core.PushNotificationsInterceptor
import com.shifthackz.android.ntfy.interceptor.common.model.PushNotification
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.koin.android.ext.android.inject
import timber.log.Timber

class PushNotificationsInterceptorService : NotificationListenerService() {

    private val interceptor: PushNotificationsInterceptor by inject()

    private val serviceScope = CoroutineScope(
        SupervisorJob() +
                Dispatchers.IO +
                CoroutineExceptionHandler { _, e -> Timber.e(e, "Error occurred!") }
    )

    override fun onCreate() {
        Timber.i("Interceptor service successfully created!")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        if (sbn == null) {
            Timber.w("Status bar notification is null, skipping.")
            return
        }

        val pushNotification = PushNotification(sbn)
        Timber.i("Delivering push notification to interceptor. Seed: ${pushNotification.hashCode()}")
        interceptor.onNewNotification(serviceScope, pushNotification)
    }

    override fun onDestroy() {
        serviceScope.cancel("PushNotificationsInterceptorService has been destroyed!")
        super.onDestroy()
        Timber.i("Interceptor service has beed destroyed!")
    }
}
