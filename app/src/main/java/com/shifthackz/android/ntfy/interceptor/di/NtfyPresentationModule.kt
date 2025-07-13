package com.shifthackz.android.ntfy.interceptor.di

import com.shifthackz.android.ntfy.interceptor.ui.screen.home.HomeViewModel
import com.shifthackz.android.ntfy.interceptor.ui.screen.log.LogViewModel
import com.shifthackz.android.ntfy.interceptor.ui.screen.settings.SettingsViewModel
import com.shifthackz.android.ntfy.interceptor.ui.screen.notifications.NotificationsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ntfyPresentationModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::SettingsViewModel)
    viewModelOf(::NotificationsViewModel)
    viewModelOf(::LogViewModel)
}
