package com.shifthackz.android.ntfy.interceptor.request

import kotlinx.serialization.Serializable

@Serializable
internal data class PostNotificationRequest(
    val topic: String,
    val title: String,
    val message: String,
    val priority: Int,
)
