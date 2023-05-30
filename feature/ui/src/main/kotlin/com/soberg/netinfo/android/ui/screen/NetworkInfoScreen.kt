package com.soberg.netinfo.android.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.soberg.netinfo.android.ui.core.preview.A11yPreview
import com.soberg.netinfo.android.ui.core.preview.ThemedPreview

@Composable
fun NetworkInfoScreen(
    viewModel: NetworkInfoViewModel,
) {
    val state by viewModel.stateFlow.collectAsState()
    NetworkInfoScreen(state)
}

@Composable
fun NetworkInfoScreen(
    state: NetworkInfoViewModel.State,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        when (state) {
            is NetworkInfoViewModel.State.NoConnectionsFound,
            is NetworkInfoViewModel.State.Loading -> {
                // TODO Add Loading
            }

            is NetworkInfoViewModel.State.Ready -> {
                WanCard(
                    state = state.wan,
                    onCopyWanIpClicked = {
                        // TODO copy ip
                    },
                    onLocationClicked = {
                        // TODO open maps
                    }
                )
            }
        }
    }
}

@A11yPreview
@Composable
private fun NetworkInfoScreenPreview() = ThemedPreview {
    NetworkInfoScreen(
        state = NetworkInfoViewModel.State.Ready(
            wan = NetworkInfoViewModel.WanState.Ready(
                ipAddress = "109.123.654.321",
                locationText = "New York NY, US"
            ),
        )
    )
}