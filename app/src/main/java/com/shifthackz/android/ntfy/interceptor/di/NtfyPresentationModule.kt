package com.shifthackz.android.ntfy.interceptor.di

import com.shifthackz.android.ntfy.interceptor.ui.screen.main.MainScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ntfyPresentationModule = module {
    viewModelOf(::MainScreenViewModel)
}
