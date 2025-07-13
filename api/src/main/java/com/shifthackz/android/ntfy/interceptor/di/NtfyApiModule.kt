package com.shifthackz.android.ntfy.interceptor.di

import com.shifthackz.android.ntfy.interceptor.api.NtfyApi
import com.shifthackz.android.ntfy.interceptor.api.NtfyApiImpl
import com.shifthackz.android.ntfy.interceptor.security.NtfyApiBaseUrlProvider
import com.shifthackz.android.ntfy.interceptor.security.NtfyApiCredentialsProvider
import com.shifthackz.android.ntfy.interceptor.security.NtfyApiTopicProvider
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module
import timber.log.Timber

val ntfyApiModule = module {

    single {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.d(message)
                    }
                }
                level = LogLevel.ALL
            }
        }
    }

    single<NtfyApi> {
        NtfyApiImpl(
            client = get(),
            baseUrlProvider = get<NtfyApiBaseUrlProvider>(named("NtfyApiBaseUrlProvider")),
            credentialsProvider = get<NtfyApiCredentialsProvider>(named("NtfyApiCredentialsProvider")),
            topicProvider = get<NtfyApiTopicProvider>(named("NtfyApiTopicProvider"))
        )
    }
}
