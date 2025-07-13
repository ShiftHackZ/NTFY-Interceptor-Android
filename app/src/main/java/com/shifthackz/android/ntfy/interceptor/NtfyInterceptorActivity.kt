package com.shifthackz.android.ntfy.interceptor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.shifthackz.android.ntfy.interceptor.ui.screen.home.HomeScreen
import com.shifthackz.android.ntfy.interceptor.ui.theme.NTFYInterceptorTheme

class NtfyInterceptorActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NTFYInterceptorTheme {
                HomeScreen()
            }
        }
    }
}
