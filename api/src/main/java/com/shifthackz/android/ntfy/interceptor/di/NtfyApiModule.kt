package com.shifthackz.android.ntfy.interceptor.di

import android.util.Log
import com.shifthackz.android.ntfy.interceptor.api.NtfyApi
import com.shifthackz.android.ntfy.interceptor.api.NtfyApiImpl
import com.shifthackz.android.ntfy.interceptor.security.NtfyApiBaseUrlProvider
import com.shifthackz.android.ntfy.interceptor.security.NtfyApiCredentialsProvider
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
                        Log.d("HTTP", message)
                    }
                }
                level = LogLevel.ALL
            }
        }
    }

    single<NtfyApi> {
        NtfyApiImpl(
            get(),
            get<NtfyApiBaseUrlProvider>(named("NtfyApiBaseUrlProvider")),
            get<NtfyApiCredentialsProvider>(named("NtfyApiCredentialsProvider"))
        )
    }
}
