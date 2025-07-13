@file:OptIn(ExperimentalMaterial3Api::class)

package com.shifthackz.android.ntfy.interceptor.ui.screen.notifications

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shifthackz.android.ntfy.interceptor.R
import com.shifthackz.android.ntfy.interceptor.common.model.PushNotification
import com.shifthackz.android.ntfy.interceptor.ui.components.ListComponent
import com.shifthackz.android.ntfy.interceptor.ui.components.ListEmptyStateComponent
import com.shifthackz.android.ntfy.interceptor.ui.components.NotificationListItem
import com.shifthackz.android.ntfy.interceptor.ui.components.TopHomeRouteAppBar
import com.shifthackz.android.ntfy.interceptor.ui.screen.home.HomeRoute
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random

@Composable
@Preview
fun NotificationsScreen(
    modifier: Modifier = Modifier,
    viewModel: NotificationsViewModel = koinViewModel(),
) {
    val notifications by viewModel.pushNotifications.collectAsStateWithLifecycle(emptyList())

    Scaffold(
        modifier = modifier,
        topBar = {
            TopHomeRouteAppBar(route = HomeRoute.Notifications)
        }
    ) { paddingValues ->
        ListComponent(
            list = notifications,
            key = { "${it.timestamp}_${it.title}_${it.body}".hashCode() * Random.nextInt() },
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            emptyState = {
                ListEmptyStateComponent(
                    title = "No notifications yet...",
                    subTitle = "Notifications will be shown here as soon as Android OS will receive them.",
                )
            },
            content = { notification ->
                NotificationListItem(notification = notification)
            }
        )
    }
}
