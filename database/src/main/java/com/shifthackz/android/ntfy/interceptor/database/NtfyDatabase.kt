package com.shifthackz.android.ntfy.interceptor.database

import com.shifthackz.android.ntfy.interceptor.common.model.PushNotification
import com.shifthackz.android.ntfy.interceptor.common.model.Log as LogDomain
import kotlinx.coroutines.flow.Flow

sealed interface NtfyDatabase {

    interface Notification : NtfyDatabase {

        fun observeAll(): Flow<List<PushNotification>>

        suspend fun insert(value: PushNotification)
    }

    interface Log : NtfyDatabase {

        fun observeAll(): Flow<List<LogDomain>>

        suspend fun insert(value: LogDomain)
    }
}
