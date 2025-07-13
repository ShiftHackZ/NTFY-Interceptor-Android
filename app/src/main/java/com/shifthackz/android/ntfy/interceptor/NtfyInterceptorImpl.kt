package com.shifthackz.android.ntfy.interceptor

import com.shifthackz.android.ntfy.interceptor.common.model.PushNotification
import com.shifthackz.android.ntfy.interceptor.core.PushNotificationsInterceptor
import com.shifthackz.android.ntfy.interceptor.repository.NotificationsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NtfyInterceptorImpl(
    private val notificationsRepository: NotificationsRepository,
) : PushNotificationsInterceptor {

    override fun onNewNotification(serviceScope: CoroutineScope, notification: PushNotification) {
        serviceScope.launch {
            notificationsRepository.publishNotification(notification)
        }
    }
}
