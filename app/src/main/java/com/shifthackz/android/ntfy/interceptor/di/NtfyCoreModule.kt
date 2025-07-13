package com.shifthackz.android.ntfy.interceptor.di

import com.shifthackz.android.ntfy.interceptor.core.randomness.SecureRandomCharMapNoiseGenerator
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val ntfyCoreModule = module {
    factoryOf(::SecureRandomCharMapNoiseGenerator)
}
