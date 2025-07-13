package com.shifthackz.android.ntfy.interceptor.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shifthackz.android.ntfy.interceptor.ui.theme.NTFYInterceptorTheme

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Preview(name = "85%", fontScale = 0.85f, apiLevel = 35)
@Preview(name = "100%", fontScale = 1.0f, apiLevel = 35)
@Preview(name = "115%", fontScale = 1.15f, apiLevel = 35)
@Preview(name = "130%", fontScale = 1.3f, apiLevel = 35)
@Preview(name = "150%", fontScale = 1.5f, apiLevel = 35)
@Preview(name = "180%", fontScale = 1.8f, apiLevel = 35)
@Preview(name = "200%", fontScale = 2f, apiLevel = 35)
annotation class PreviewFontScaleApi35

@Composable
fun ThemedPreview(
    content: @Composable () -> Unit,
) {
    NTFYInterceptorTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            content()
        }
    }
}
