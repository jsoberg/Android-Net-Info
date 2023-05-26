package com.soberg.netinfo.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import com.soberg.netinfo.android.ui.core.theme.AppTheme
import com.soberg.netinfo.android.ui.screen.NetworkInfoScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                SystemUISetup()

                Surface {
                    NetworkInfoScreen(
                        viewModel = hiltViewModel(),
                    )
                }

            }
        }
    }
}