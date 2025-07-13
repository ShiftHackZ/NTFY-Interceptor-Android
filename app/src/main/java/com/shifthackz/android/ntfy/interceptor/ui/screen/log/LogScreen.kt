package com.shifthackz.android.ntfy.interceptor.ui.screen.log

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shifthackz.android.ntfy.interceptor.ui.components.ListComponent
import com.shifthackz.android.ntfy.interceptor.ui.components.ListEmptyStateComponent
import com.shifthackz.android.ntfy.interceptor.ui.components.LogListItem
import com.shifthackz.android.ntfy.interceptor.ui.components.TopHomeRouteAppBar
import com.shifthackz.android.ntfy.interceptor.ui.screen.home.HomeRoute
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random

@Composable
@Preview
fun LogScreen(
    modifier: Modifier = Modifier,
    viewModel: LogViewModel = koinViewModel(),
) {
    val logs by viewModel.logs.collectAsStateWithLifecycle(emptyList())

    Scaffold(
        modifier = modifier,
        topBar = {
            TopHomeRouteAppBar(route = HomeRoute.Logs)
        }
    ) { paddingValues ->
        ListComponent(
            list = logs,
            key = { "${it.timestamp}_${it.tag}_${it.level}".hashCode() * Random.nextInt() },
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            emptyState = {
                ListEmptyStateComponent(
                    title = "No logs yet...",
                    subTitle = "Logs will be shown here as soon as NTFY Interceptor application will capture them.",
                )
            },
            content = { log ->
                LogListItem(log = log)
            }
        )
    }
}
