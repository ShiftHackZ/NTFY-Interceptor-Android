package com.shifthackz.android.ntfy.interceptor.core.extensions

import android.content.Context

val Context.appVersionName: String?
    get() = packageManager.getPackageInfo(packageName, 0).versionName

val Context.appVersionCode: Long
    get() = packageManager.getPackageInfo(packageName, 0).longVersionCode

val Context.appVersionFormatted: String
    get() = "$appVersionName (${appVersionCode})"
