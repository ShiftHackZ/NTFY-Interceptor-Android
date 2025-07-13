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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shifthackz.android.ntfy.interceptor.ui.screen.log.LogScreen
import com.shifthackz.android.ntfy.interceptor.ui.screen.settings.SettingsScreen
import com.shifthackz.android.ntfy.interceptor.ui.screen.notifications.NotificationsScreen
import org.koin.androidx.compose.koinViewModel

@Composable
@Preview
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val route by viewModel.route.collectAsStateWithLifecycle()
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                HomeRoute.entries.forEach { entry ->
                    NavigationBarItem(
                        selected = route == entry,
                        onClick = { viewModel.navigate(entry) },
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
            }
        }
        LaunchedEffect(route) {
            pagerState.animateScrollToPage(HomeRoute.entries.indexOf(route))
        }
    }
}
