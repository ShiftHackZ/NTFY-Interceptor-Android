package com.shifthackz.android.ntfy.interceptor.core

import com.shifthackz.android.ntfy.interceptor.core.model.PushNotification
import kotlinx.coroutines.CoroutineScope

fun interface PushNotificationsInterceptor {
    fun onNewNotification(serviceScope: CoroutineScope, notification: PushNotification)
}
