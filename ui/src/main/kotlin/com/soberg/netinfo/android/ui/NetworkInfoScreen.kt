package com.soberg.netinfo.android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

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
            is NetworkInfoViewModel.State.CannotConnect,
            is NetworkInfoViewModel.State.Loading -> {
                // TODO Add Loading
            }
            is NetworkInfoViewModel.State.Connected -> {
                NetworkInfoContent(
                    wanIpAddress = state.wanIpAddress,
                    onCopyWanIpClicked = {
                        // TODO copy ip
                    },
                )
            }
        }
    }
}

@Composable
fun NetworkInfoContent(
    wanIpAddress: String,
    onCopyWanIpClicked: (String) -> Unit,
) {
    CopyableTextRow(
        text = wanIpAddress,
        onCopyTextClicked = onCopyWanIpClicked,
    )
}