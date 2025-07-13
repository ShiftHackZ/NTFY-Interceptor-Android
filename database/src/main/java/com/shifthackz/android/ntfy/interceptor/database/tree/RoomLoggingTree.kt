package com.shifthackz.android.ntfy.interceptor.database.tree

import android.util.Log
import com.shifthackz.android.ntfy.interceptor.common.model.Log as LogDomain
import com.shifthackz.android.ntfy.interceptor.database.NtfyDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber

class RoomLoggingTree : Timber.Tree(), KoinComponent {

    private val ntfyLogDb: NtfyDatabase.Log by inject()

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val level = when (priority) {
            Log.VERBOSE -> "VERBOSE"
            Log.DEBUG -> "DEBUG"
            Log.INFO -> "INFO"
            Log.WARN -> "WARN"
            Log.ERROR -> "ERROR"
            else -> "UNKNOWN"
        }

        GlobalScope.launch {
            ntfyLogDb.insert(LogDomain(tag ?: "NTFYI", message, level))
        }
    }
}
