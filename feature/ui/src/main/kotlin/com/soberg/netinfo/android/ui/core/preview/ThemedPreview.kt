package com.soberg.netinfo.android.ui.core.preview

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.soberg.netinfo.android.ui.core.theme.AppTheme

@Composable
fun ThemedPreview(content: @Composable () -> Unit) = AppTheme {
    Surface {
        content()
    }
}