@file:OptIn(ExperimentalMaterial3Api::class)

package com.shifthackz.android.ntfy.interceptor.ui.screen.notifications

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
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
    HomeScreenContent(notifications, modifier)
}

@Composable
private fun HomeScreenContent(
    notifications: List<PushNotification>,
    modifier: Modifier = Modifier,
) {
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
                    title = stringResource(R.string.notifications_empty_state_title),
                    subTitle = stringResource(R.string.notifications_empty_state_sub_title),
                )
            },
            content = { notification ->
                NotificationListItem(notification = notification)
            }
        )
    }
}

@Composable
@PreviewFontScale
private fun HomeScreenPreview() {
    HomeScreenContent(
        notifications = buildList {
            repeat(5) {
                add(
                    PushNotification(
                        packageName = "com.shifthackz.aisdv1.app",
                        title = "Generation successful!",
                        body = "Your image [$it] was successfully generated.",
                    )
                )
            }
        }
    )
}

@Composable
@PreviewFontScale
private fun HomeScreenEmptyStatePreview() {
    HomeScreenContent(emptyList())
}
