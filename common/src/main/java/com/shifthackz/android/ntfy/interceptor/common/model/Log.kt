package com.shifthackz.android.ntfy.interceptor.common.model

import androidx.compose.runtime.Stable

@Stable
data class Log(
    val tag: String = "",
    val message: String = "",
    val level: String = "debug",
    val timestamp: Long = System.currentTimeMillis(),
)
