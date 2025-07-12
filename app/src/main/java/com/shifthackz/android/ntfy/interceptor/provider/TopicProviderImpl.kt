package com.shifthackz.android.ntfy.interceptor.provider

import com.shifthackz.android.ntfy.interceptor.security.NtfyApiTopicProvider
import com.shifthackz.android.ntfy.interceptor.settings.repository.SettingsRepository
import kotlinx.coroutines.flow.FlowCollector

class TopicProviderImpl(
    private val settingsRepository: SettingsRepository,
) : NtfyApiTopicProvider {

    override suspend fun collect(collector: FlowCollector<String>) {
        return settingsRepository.getTopic().collect(collector)
    }
}
