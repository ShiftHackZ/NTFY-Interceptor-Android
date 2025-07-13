package com.shifthackz.android.ntfy.interceptor.common.model

data class Log(
    val tag: String = "",
    val message: String = "",
    val level: String = "debug",
    val timestamp: Long = System.currentTimeMillis(),
)
