package com.shifthackz.android.ntfy.interceptor

import com.shifthackz.android.ntfy.interceptor.api.NtfyApi
import com.shifthackz.android.ntfy.interceptor.core.PushNotificationsInterceptor
import com.shifthackz.android.ntfy.interceptor.core.model.PushNotification
import com.shifthackz.android.ntfy.interceptor.model.Priority
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NtfyInterceptorImpl(
    private val api: NtfyApi,
) : PushNotificationsInterceptor {

    override fun onNewNotification(serviceScope: CoroutineScope, notification: PushNotification) {
        serviceScope.launch {
            api.postNotification(
                title = "[${notification.packageName}] ${notification.title}",
                message = notification.body,
                priority = Priority.High,
            )
        }
    }
}
