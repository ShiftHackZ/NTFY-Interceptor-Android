package com.shifthackz.android.ntfy.interceptor.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shifthackz.android.ntfy.interceptor.database.contract.LogContract

@Entity(tableName = LogContract.TABLE)
data class LogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = LogContract.ID)
    val id: Long = 0L,
    @ColumnInfo(name = LogContract.TAG)
    val tag: String,
    @ColumnInfo(name = LogContract.MESSAGE)
    val message: String,
    @ColumnInfo(name = LogContract.LEVEL)
    val level: String,
    @ColumnInfo(name = LogContract.TIMESTAMP)
    val timestamp: Long = System.currentTimeMillis()
)
