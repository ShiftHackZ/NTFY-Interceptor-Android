package com.shifthackz.android.ntfy.interceptor.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class DeviceBootedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent == null) {
            Log.w("NTFYI", "Boot receiver got null intent.")
            return
        }

        if (intent.action != Intent.ACTION_BOOT_COMPLETED) {
            Log.w("NTFYI", "Boot receiver got intent with wrong action.")
            return
        }

        Log.i("NTFYI", "Device boot complete.")
    }
}
