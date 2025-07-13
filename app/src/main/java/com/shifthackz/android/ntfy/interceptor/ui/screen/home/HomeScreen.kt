@file:Suppress("UnusedMaterial3ScaffoldPaddingParameter")

package com.shifthackz.android.ntfy.interceptor.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shifthackz.android.ntfy.interceptor.ui.screen.apps.AppsSettingsScreen
import com.shifthackz.android.ntfy.interceptor.ui.screen.log.LogScreen
import com.shifthackz.android.ntfy.interceptor.ui.screen.settings.SettingsScreen
import com.shifthackz.android.ntfy.interceptor.ui.screen.notifications.NotificationsScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val route by viewModel.route.collectAsStateWithLifecycle()
    HomeScreenContent(
        route = route,
        modifier = modifier,
        onNavigate = viewModel::navigate,
    )
}

@Composable
private fun HomeScreenContent(
    route: HomeRoute,
    modifier: Modifier = Modifier,
    onNavigate: (HomeRoute) -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                HomeRoute.entries.forEach { entry ->
                    NavigationBarItem(
                        selected = route == entry,
                        onClick = { onNavigate(entry) },
                        icon = {
                            Icon(
                                imageVector = entry.imageVector,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(entry.textResId)
                            )
                        }
                    )
                }
            }
        }
    ) {
        val pagerState = rememberPagerState { HomeRoute.entries.size }
        HorizontalPager(
            modifier = Modifier.padding(),
            state = pagerState,
            key = { it },
            userScrollEnabled = false,
            beyondViewportPageCount = HomeRoute.entries.size,
        ) { index ->
            when (HomeRoute.entries[index]) {
                HomeRoute.Notifications -> NotificationsScreen()
                HomeRoute.Settings -> SettingsScreen()
                HomeRoute.Logs -> LogScreen()
                HomeRoute.Apps -> AppsSettingsScreen()
            }
        }
        LaunchedEffect(route) {
            pagerState.animateScrollToPage(HomeRoute.entries.indexOf(route))
        }
    }
}

@Composable
@PreviewFontScale
private fun HomeScreenPreview() {
    HomeScreenContent(HomeRoute.entries.first())
}
