package com.shifthackz.android.ntfy.interceptor.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shifthackz.android.ntfy.interceptor.database.contract.PushNotificationContract
import com.shifthackz.android.ntfy.interceptor.database.entity.PushNotificationEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface PushNotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(value: PushNotificationEntity)

    @Query("SELECT * FROM ${PushNotificationContract.TABLE} ORDER BY ${PushNotificationContract.TIMESTAMP} DESC")
    fun observeAll(): Flow<List<PushNotificationEntity>>
}
