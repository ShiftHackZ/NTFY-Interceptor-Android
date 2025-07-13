@file:OptIn(ExperimentalMaterial3Api::class)

package com.shifthackz.android.ntfy.interceptor.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shifthackz.android.ntfy.interceptor.ui.screen.home.HomeRoute

@Composable
fun TopHomeRouteAppBar(
    modifier: Modifier = Modifier,
    route: HomeRoute
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                imageVector = route.imageVector,
                contentDescription = null,
            )
        },
        title = {
            Text(
                text = stringResource(route.textResId)
            )
        }
    )
}
