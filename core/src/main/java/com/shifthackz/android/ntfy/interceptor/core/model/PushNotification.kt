package com.shifthackz.android.ntfy.interceptor.core.model

import android.app.Notification
import android.service.notification.StatusBarNotification

data class PushNotification(
    val packageName: String,
    val title: String,
    val body: String,
) {
    constructor(sbn: StatusBarNotification) : this(
        packageName = sbn.packageName,
        title = sbn.notification.extras.getString(Notification.EXTRA_TITLE) ?: "",
        body = sbn.notification.extras.getString(Notification.EXTRA_TEXT) ?: ""
    )
}

