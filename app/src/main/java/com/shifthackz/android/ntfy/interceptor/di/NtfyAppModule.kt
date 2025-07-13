package com.shifthackz.android.ntfy.interceptor.di

import com.shifthackz.android.ntfy.interceptor.database.di.ntfyDatabaseModule
import com.shifthackz.android.ntfy.interceptor.settings.di.ntfySettingsModule

val ntfyAppModule = ntfyCoreModule +
        ntfySettingsModule +
        ntfyProvidersModule +
        ntfyApiModule +
        ntfyDatabaseModule +
        ntfyRepositoryModule +
        ntfyInterceptorsModule +
        ntfyPresentationModule
