package com.shifthackz.android.ntfy.interceptor.database

import com.shifthackz.android.ntfy.interceptor.common.model.PushNotification
import com.shifthackz.android.ntfy.interceptor.common.model.Log
import com.shifthackz.android.ntfy.interceptor.database.entity.LogEntity
import com.shifthackz.android.ntfy.interceptor.database.entity.PushNotificationEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal data class NtfyDatabaseImpl(
    private val roomDatabase: NtfyRoomDatabase,
) : NtfyDatabase {

    internal inner class NotificationImpl : NtfyDatabase.Notification {
        override fun observeAll(): Flow<List<PushNotification>> =
            roomDatabase.pushNotificationDao().observeAll().map { list ->
                list.map { PushNotification(it.packageName, it.title, it.body, it.timestamp) }
            }

        override suspend fun insert(value: PushNotification) =
            roomDatabase.pushNotificationDao().insert(
                PushNotificationEntity(
                    packageName = value.packageName,
                    title = value.title,
                    body = value.body,
                    timestamp = value.timestamp,
                )
            )
    }

    internal inner class LogImpl : NtfyDatabase.Log {
        override fun observeAll(): Flow<List<Log>> =
            roomDatabase.logDao().observeAll().map { list ->
                list.map { Log(it.tag, it.message, it.level, it.timestamp) }
            }

        override suspend fun insert(value: Log) =
            roomDatabase.logDao().insert(
                LogEntity(
                    id = 0,
                    tag = value.tag,
                    message = value.message,
                    level = value.level,
                    timestamp = value.timestamp,
                )
            )

    }
}
