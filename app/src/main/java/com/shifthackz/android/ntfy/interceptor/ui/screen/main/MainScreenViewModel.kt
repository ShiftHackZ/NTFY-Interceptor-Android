package com.shifthackz.android.ntfy.interceptor.ui.screen.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shifthackz.android.ntfy.interceptor.R
import com.shifthackz.android.ntfy.interceptor.settings.model.NtfyPreferences
import com.shifthackz.android.ntfy.interceptor.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    private val _baseUrl = MutableStateFlow(TextFieldValue(""))
    val baseUrl = _baseUrl.asStateFlow()

    private val _username = MutableStateFlow(TextFieldValue(""))
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow(TextFieldValue(""))
    val password = _password.asStateFlow()

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible = _passwordVisible.asStateFlow()

    init {
        viewModelScope.launch {
            settingsRepository.observe().first()
                .let {
                    _baseUrl.value = TextFieldValue(it.baseUrl)
                    _username.value = TextFieldValue(it.username)
                    _password.value = TextFieldValue(it.password)

                }
        }

    }

    fun updateBaseUrl(value: TextFieldValue) = viewModelScope.launch {
        _baseUrl.update { value }
        settingsRepository.saveBaseUrl(value.text)
    }

    fun updateUsername(value: TextFieldValue) = viewModelScope.launch {
        _username.update { value }
        settingsRepository.saveUsername(value.text)
    }

    fun updatePassword(value: TextFieldValue) = viewModelScope.launch {
        _password.update { value }
        settingsRepository.savePassword(value.text)
    }

    fun togglePasswordVisibility() = _passwordVisible.update { !it }

    fun setupNecessaryPermissions(context: Context) {
        val permissionsIntent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
        context.startActivity(permissionsIntent)
    }

    fun sendTestLocalNotification(context: Context) {
        val seed = System.currentTimeMillis().toInt()

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(context.getString(R.string.test_notification_title))
            .setContentText(context.getString(R.string.test_notification_body, "$seed"))
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()

        val channel = NotificationChannel(
            CHANNEL_ID,
            "NTFY Interceptor",
            NotificationManager.IMPORTANCE_HIGH
        )

        with(context.getSystemService(NotificationManager::class.java)) {
            createNotificationChannel(channel)
            notify(seed, notification)
        }
    }

    companion object {
        private const val CHANNEL_ID = "ntfy_interceptor_notification_channel"
    }
}
