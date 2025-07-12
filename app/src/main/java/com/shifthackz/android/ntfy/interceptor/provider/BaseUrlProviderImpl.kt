package com.shifthackz.android.ntfy.interceptor.provider

import com.shifthackz.android.ntfy.interceptor.security.NtfyApiBaseUrlProvider
import com.shifthackz.android.ntfy.interceptor.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.FlowCollector

class BaseUrlProviderImpl(
    private val settingsRepository: SettingsRepository,
) : NtfyApiBaseUrlProvider {

    override suspend fun collect(collector: FlowCollector<String>) {
        return settingsRepository.getBaseUrl().collect(collector)
    }
}
