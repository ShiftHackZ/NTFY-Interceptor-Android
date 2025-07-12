package com.shifthackz.android.ntfy.interceptor.di

import com.shifthackz.android.ntfy.interceptor.settings.di.ntfySettingsModule

val ntfyAppModule = ntfySettingsModule +
        ntfyProvidersModule +
        ntfyApiModule +
        ntfyPresentationModule
