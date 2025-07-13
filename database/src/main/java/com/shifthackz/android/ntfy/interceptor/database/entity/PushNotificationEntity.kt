package com.shifthackz.android.ntfy.interceptor.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shifthackz.android.ntfy.interceptor.database.contract.PushNotificationContract


@Entity(tableName = PushNotificationContract.TABLE)
internal data class PushNotificationEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PushNotificationContract.ID)
    val id: Long = 0,
    @ColumnInfo(name = PushNotificationContract.PACKAGE)
    val packageName: String,
    @ColumnInfo(name = PushNotificationContract.TITLE)
    val title: String,
    @ColumnInfo(name = PushNotificationContract.BODY)
    val body: String,
    @ColumnInfo(name = PushNotificationContract.TIMESTAMP)
    val timestamp: Long = System.currentTimeMillis(),
)
