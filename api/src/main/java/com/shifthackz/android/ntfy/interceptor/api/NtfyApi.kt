package com.shifthackz.android.ntfy.interceptor.api

import com.shifthackz.android.ntfy.interceptor.model.Priority

interface NtfyApi {

    suspend fun postNotification(
        title: String,
        message: String,
        priority: Priority = Priority.Default,
    ): Result<Unit>
}
