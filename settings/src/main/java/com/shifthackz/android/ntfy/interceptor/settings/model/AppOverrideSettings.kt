package com.shifthackz.android.ntfy.interceptor.settings.model

import kotlinx.serialization.Serializable

@Serializable
data class AppOverrideSettings(
    val isEnabled: Boolean = true,
    val customTopic: String = ""
)
