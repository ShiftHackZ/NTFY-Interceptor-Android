package com.shifthackz.android.ntfy.interceptor.ui.screen.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeveloperMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.shifthackz.android.ntfy.interceptor.R

enum class HomeRoute(
    val imageVector: ImageVector,
    @StringRes val textResId: Int,
) {
    Notifications(
        Icons.Default.Notifications,
        R.string.bottom_nav_notifications,
    ),
    Logs(
        Icons.Default.DeveloperMode,
        R.string.bottom_nav_logs,
    ),
    Settings(
        Icons.Default.Settings,
        R.string.bottom_nav_settings,
    ),
}
