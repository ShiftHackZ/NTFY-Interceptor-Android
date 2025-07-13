package com.shifthackz.android.ntfy.interceptor.ui.screen.apps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shifthackz.android.ntfy.interceptor.common.model.InstalledApp
import com.shifthackz.android.ntfy.interceptor.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppsSettingsViewModel(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    private val _apps = MutableStateFlow<List<InstalledApp>>(emptyList())
    val apps = _apps.asStateFlow()

    init {
        viewModelScope.launch {
            settingsRepository.observeInstalledApps().first().let {
                _apps.value = it
            }
        }
    }

    fun toggleOverride(app: InstalledApp, isEnabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.updateAppOverride(app.packageName, isEnabled)
            _apps.update { list ->
                list.map {
                   if (it.packageName != app.packageName) it
                   else app.copy(isEnabled = isEnabled)
                }
            }
        }
    }
}
