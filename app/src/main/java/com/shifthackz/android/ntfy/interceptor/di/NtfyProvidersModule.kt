package com.shifthackz.android.ntfy.interceptor.di

import com.shifthackz.android.ntfy.interceptor.provider.BaseUrlProviderImpl
import com.shifthackz.android.ntfy.interceptor.provider.CredentialsProviderImpl
import com.shifthackz.android.ntfy.interceptor.security.NtfyApiBaseUrlProvider
import com.shifthackz.android.ntfy.interceptor.security.NtfyApiCredentialsProvider
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ntfyProvidersModule = module {

    single<NtfyApiCredentialsProvider>(named("NtfyApiCredentialsProvider")) {
        CredentialsProviderImpl(get())
    }

    single<NtfyApiBaseUrlProvider>(named("NtfyApiBaseUrlProvider")) {
        BaseUrlProviderImpl(get())
    }
}
