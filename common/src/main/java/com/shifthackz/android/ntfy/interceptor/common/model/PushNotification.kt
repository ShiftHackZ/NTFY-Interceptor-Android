package com.shifthackz.android.ntfy.interceptor.common.model

import android.app.Notification
import android.service.notification.StatusBarNotification

data class PushNotification(
    val packageName: String = "",
    val title: String = "",
    val body: String = "",
    val timestamp: Long = System.currentTimeMillis(),
) {
    constructor(sbn: StatusBarNotification) : this(
        packageName = sbn.packageName,
        title = sbn.notification.extras.getString(Notification.EXTRA_TITLE) ?: "",
        body = sbn.notification.extras.getString(Notification.EXTRA_TEXT) ?: ""
    )

    val isEmpty: Boolean
        get() = title.isEmpty() && body.isEmpty()

    val isNotEmpty: Boolean
        get() = !isEmpty
}
