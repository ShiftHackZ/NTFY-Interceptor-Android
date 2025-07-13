package com.shifthackz.android.ntfy.interceptor.common.model

import android.graphics.drawable.Drawable
import androidx.compose.runtime.Stable

@Stable
data class InstalledApp(
    val appName: String,
    val packageName: String,
    val icon: Drawable,
    val isEnabled: Boolean = true,
    val topic: String = "",
)
