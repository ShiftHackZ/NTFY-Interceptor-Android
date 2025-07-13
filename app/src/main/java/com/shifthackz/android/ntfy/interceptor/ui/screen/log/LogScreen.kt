package com.shifthackz.android.ntfy.interceptor.ui.screen.log

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shifthackz.android.ntfy.interceptor.R
import com.shifthackz.android.ntfy.interceptor.common.model.Log
import com.shifthackz.android.ntfy.interceptor.ui.components.ListComponent
import com.shifthackz.android.ntfy.interceptor.ui.components.ListEmptyStateComponent
import com.shifthackz.android.ntfy.interceptor.ui.components.LogListItem
import com.shifthackz.android.ntfy.interceptor.ui.components.TopHomeRouteAppBar
import com.shifthackz.android.ntfy.interceptor.ui.screen.home.HomeRoute
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random

@Composable
fun LogScreen(
    modifier: Modifier = Modifier,
    viewModel: LogViewModel = koinViewModel(),
) {
    val logs by viewModel.logs.collectAsStateWithLifecycle(emptyList())
    LogScreenContent(logs, modifier)
}

@Composable
private fun LogScreenContent(
    logs: List<Log>,
    modifier: Modifier = Modifier,
) {
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
                    title = stringResource(R.string.logs_empty_state_title),
                    subTitle = stringResource(R.string.logs_empty_state_sub_title),
                )
            },
            content = { log ->
                LogListItem(log = log)
            }
        )
    }
}

@Composable
@PreviewFontScale
private fun LogScreenPreview() {
    LogScreenContent(
        logs = buildList {
            repeat(5) {
                add(
                    Log(
                        tag = "NTFYI",
                        message = "Service [$it] has been created!",
                        level = "info",
                    ),
                )
            }
        }
    )
}

@Composable
@PreviewFontScale
private fun LogScreenEmptyStatePreview() {
    LogScreenContent(emptyList())
}
