package com.shifthackz.android.ntfy.interceptor.ui.screen.apps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shifthackz.android.ntfy.interceptor.R
import com.shifthackz.android.ntfy.interceptor.common.model.InstalledApp
import com.shifthackz.android.ntfy.interceptor.ui.components.AppSettingItem
import com.shifthackz.android.ntfy.interceptor.ui.components.ListComponent
import com.shifthackz.android.ntfy.interceptor.ui.components.ListEmptyStateComponent
import com.shifthackz.android.ntfy.interceptor.ui.components.TopHomeRouteAppBar
import com.shifthackz.android.ntfy.interceptor.ui.screen.home.HomeRoute
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppsSettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: AppsSettingsViewModel = koinViewModel(),
) {
    val apps by viewModel.apps.collectAsStateWithLifecycle(emptyList())
    AppSettingsContent(
        apps,
        modifier,
        viewModel::toggleOverride,
    )
}

@Composable
private fun AppSettingsContent(
    apps: List<InstalledApp>,
    modifier: Modifier = Modifier,
    onOverrideToggle: (InstalledApp, Boolean) -> Unit = { _, _ -> },
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopHomeRouteAppBar(route = HomeRoute.Apps)
        }
    ) { paddingValues ->
        ListComponent(
            list = apps,
            key = { it.packageName },
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            emptyState = {
                ListEmptyStateComponent(
                    title = stringResource(R.string.apps_empty_state_title),
                    subTitle = stringResource(R.string.logs_empty_state_sub_title),
                )
            },
            content = { app ->
                AppSettingItem(
                    app = app,
                    onOverrideToggle = { onOverrideToggle(app, it) },
                )
            }
        )
    }
}
