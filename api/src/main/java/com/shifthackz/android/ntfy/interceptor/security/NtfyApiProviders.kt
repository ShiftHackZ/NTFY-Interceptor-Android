package com.shifthackz.android.ntfy.interceptor.security

import kotlinx.coroutines.flow.Flow

typealias NtfyApiCredentialsProvider = Flow<Pair<String, String>>

typealias NtfyApiBaseUrlProvider = Flow<String>
