package com.shifthackz.android.ntfy.interceptor.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shifthackz.android.ntfy.interceptor.database.contract.LogContract
import com.shifthackz.android.ntfy.interceptor.database.entity.LogEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface LogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: LogEntity)

    @Query("SELECT * FROM ${LogContract.TABLE} ORDER BY ${LogContract.TIMESTAMP} DESC")
    fun observeAll(): Flow<List<LogEntity>>
}
