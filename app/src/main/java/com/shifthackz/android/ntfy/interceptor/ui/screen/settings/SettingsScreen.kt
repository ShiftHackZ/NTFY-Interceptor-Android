@file:OptIn(ExperimentalMaterial3Api::class)

package com.shifthackz.android.ntfy.interceptor.ui.screen.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shifthackz.android.ntfy.interceptor.R
import org.koin.androidx.compose.koinViewModel
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.shifthackz.android.ntfy.interceptor.core.extensions.appVersionFormatted
import com.shifthackz.android.ntfy.interceptor.ui.components.TopHomeRouteAppBar
import com.shifthackz.android.ntfy.interceptor.ui.screen.home.HomeRoute

@Composable
@Preview
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val baseUrl by viewModel.baseUrl.collectAsStateWithLifecycle()
    val topic by viewModel.topic.collectAsStateWithLifecycle()
    val username by viewModel.username.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val passwordVisible by viewModel.passwordVisible.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopHomeRouteAppBar(route = HomeRoute.Settings)
//            TopAppBar(
//                title = {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically,
//                    ) {


//                    }
//                }
//            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .imePadding()
                .navigationBarsPadding()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = baseUrl,
                onValueChange = viewModel::updateBaseUrl,
                label = { Text(stringResource(R.string.hint_base_url)) },
                maxLines = 1,
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = topic,
                onValueChange = viewModel::updateTopic,
                label = { Text(stringResource(R.string.hint_topic)) },
                maxLines = 1,
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = viewModel::updateUsername,
                label = { Text(stringResource(R.string.hint_username)) },
                maxLines = 1,
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = viewModel::updatePassword,
                label = { Text(stringResource(R.string.hint_password)) },
                maxLines = 1,
                visualTransformation = if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    IconButton(onClick = viewModel::togglePasswordVisibility) {
                        Icon(
                            imageVector = if (passwordVisible) {
                                Icons.Default.Visibility
                            } else {
                                Icons.Default.VisibilityOff
                            },
                            contentDescription = null,
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.setupNecessaryPermissions(context)
                },
            ) {
                Text(stringResource(R.string.action_setup_permissions))
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.sendTestLocalNotification(context)
                },
            ) {
                Text(stringResource(R.string.action_send_test_notification))
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier.size(44.dp),
                    painter = painterResource(R.drawable.ic_app_logo),
                    contentDescription = null,
                )
                Column {
                    Text(stringResource(R.string.app_name))
                    Text(
                        text = context.appVersionFormatted,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W300,
                    )
                }
            }
        }
    }
}
