package com.shifthackz.android.ntfy.interceptor.core.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timber.log.Timber

class DeviceBootedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent == null) {
            Timber.w("Boot receiver got null intent.")
            return
        }

        if (intent.action != Intent.ACTION_BOOT_COMPLETED) {
            Timber.w("Boot receiver got intent with wrong action.")
            return
        }

        Timber.i("Device boot complete.")
    }
}
