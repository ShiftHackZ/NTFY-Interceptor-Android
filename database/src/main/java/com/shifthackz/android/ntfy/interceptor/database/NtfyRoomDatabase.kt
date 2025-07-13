package com.shifthackz.android.ntfy.interceptor.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shifthackz.android.ntfy.interceptor.database.dao.LogDao
import com.shifthackz.android.ntfy.interceptor.database.dao.PushNotificationDao
import com.shifthackz.android.ntfy.interceptor.database.entity.LogEntity
import com.shifthackz.android.ntfy.interceptor.database.entity.PushNotificationEntity

@Database(entities = [PushNotificationEntity::class, LogEntity::class], version = 1)
internal abstract class NtfyRoomDatabase: RoomDatabase() {
    abstract fun pushNotificationDao(): PushNotificationDao
    abstract fun logDao(): LogDao
}
