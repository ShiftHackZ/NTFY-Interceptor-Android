package com.shifthackz.android.ntfy.interceptor.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun <T : Any> ListComponent(
    list: List<T>,
    key: (T) -> Any,
    modifier: Modifier = Modifier,
    emptyState: @Composable () -> Unit,
    content: @Composable (T) -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(
                list,
                key = key,
            ) { t ->
                content(t)
            }

            if (list.isNotEmpty()) {
                item(key = "END") {
                    Spacer(
                        modifier = Modifier
                            .height(100.dp)
                            .navigationBarsPadding(),
                    )
                }
            }
        }
        if (list.isEmpty()) {
            Box(modifier = Modifier.align(Alignment.Center)) {
                emptyState()
            }
        }
    }
}

@Composable
fun ListEmptyStateComponent(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
) {
    Column(
        modifier = modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = subTitle,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}