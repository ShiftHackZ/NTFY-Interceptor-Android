package com.shifthackz.android.ntfy.interceptor.provider

import com.shifthackz.android.ntfy.interceptor.security.NtfyApiCredentialsProvider
import com.shifthackz.android.ntfy.interceptor.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map

class CredentialsProviderImpl(
    private val settingsRepository: SettingsRepository,
) : NtfyApiCredentialsProvider {

    override suspend fun collect(collector: FlowCollector<Pair<String, String>>) {
        return settingsRepository.observe()
            .map { it.username to it.password }
            .collect(collector)
    }
}
