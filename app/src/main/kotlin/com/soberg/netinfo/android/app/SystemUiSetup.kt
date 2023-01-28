package com.soberg.netinfo.android.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemUISetup() {
    val systemUiController = rememberSystemUiController()
    val isLightTheme = !isSystemInDarkTheme()
    val color = MaterialTheme.colorScheme.surface
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = color,
            darkIcons = isLightTheme
        )
    }
}