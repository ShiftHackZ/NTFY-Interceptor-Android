package com.shifthackz.android.ntfy.interceptor.ui.screen.settings

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
import com.shifthackz.android.ntfy.interceptor.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    private val _baseUrl = MutableStateFlow(TextFieldValue(""))
    val baseUrl = _baseUrl.asStateFlow()

    private val _topic = MutableStateFlow(TextFieldValue(""))
    val topic = _topic.asStateFlow()

    private val _username = MutableStateFlow(TextFieldValue(""))
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow(TextFieldValue(""))
    val password = _password.asStateFlow()

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible = _passwordVisible.asStateFlow()

    init {
        viewModelScope.launch {
            settingsRepository.observe().first().let { settings ->
                _baseUrl.value = TextFieldValue(settings.baseUrl)
                _username.value = TextFieldValue(settings.username)
                _password.value = TextFieldValue(settings.password)
                _topic.value = TextFieldValue(settings.topic)
            }
        }
    }

    fun updateBaseUrl(value: TextFieldValue) = viewModelScope.launch {
        _baseUrl.update { value }
        settingsRepository.saveBaseUrl(value.text)
    }

    fun updateTopic(value: TextFieldValue) = viewModelScope.launch {
        _topic.update { value }
        settingsRepository.saveTopic(value.text)
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
            context.getString(R.string.test_notification_channel_name),
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
