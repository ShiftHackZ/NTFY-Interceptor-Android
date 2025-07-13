package com.shifthackz.android.ntfy.interceptor.repository

import com.shifthackz.android.ntfy.interceptor.common.model.Log
import com.shifthackz.android.ntfy.interceptor.database.NtfyDatabase
import kotlinx.coroutines.flow.Flow

class LogRepository(private val ntfyLogDb: NtfyDatabase.Log) {

    fun observe(): Flow<List<Log>> = ntfyLogDb.observeAll()
}
