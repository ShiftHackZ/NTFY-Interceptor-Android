package com.shifthackz.android.ntfy.interceptor.api

import android.util.Log
import com.shifthackz.android.ntfy.interceptor.model.Priority
import com.shifthackz.android.ntfy.interceptor.request.PostNotificationRequest
import com.shifthackz.android.ntfy.interceptor.security.NtfyApiBaseUrlProvider
import com.shifthackz.android.ntfy.interceptor.security.NtfyApiCredentialsProvider
import com.shifthackz.android.ntfy.interceptor.security.NtfyApiTopicProvider
import io.ktor.client.HttpClient
import io.ktor.client.request.basicAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.first

internal class NtfyApiImpl(
    private val client: HttpClient,
    private val baseUrlProvider: NtfyApiBaseUrlProvider,
    private val credentialsProvider: NtfyApiCredentialsProvider,
    private val topicProvider: NtfyApiTopicProvider,
) : NtfyApi {

    override suspend fun postNotification(
        title: String,
        message: String,
        priority: Priority
    ): Result<Unit> {
        try {
            val baseUrl = baseUrlProvider.first()
            val topic = topicProvider.first()
            val (username, password) = credentialsProvider.first()
            val requestPayload = PostNotificationRequest(
                topic = topic,
                title = title,
                message = message,
                priority = priority.value,
            )
            client.post(baseUrl) {
                basicAuth(username, password)
                contentType(ContentType.Application.Json)
                setBody(requestPayload)
            }
            return Result.success(Unit)
        } catch (e: Exception) {
            Log.e("NtfyApiImpl", "Error communicating with NTFY API!", e)
            return Result.failure(e)
        }
    }
}
