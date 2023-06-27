package com.soberg.netinfo.android.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.soberg.netinfo.android.ui.core.preview.A11yPreview
import com.soberg.netinfo.android.ui.core.preview.ThemedPreview
import com.soberg.netinfo.android.ui.screen.card.LanCard
import com.soberg.netinfo.android.ui.screen.card.WanCard

@Composable
fun NetworkInfoScreen(
    viewModel: NetworkInfoViewModel,
) {
    val state by viewModel.stateFlow.collectAsState()
    NetworkInfoScreen(
        state = state,
        onLocationClicked = viewModel::onLocationClicked,
    )
}

@Composable
fun NetworkInfoScreen(
    state: NetworkInfoViewModel.State,
    onLocationClicked: () -> Unit,
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
                ReadyContent(
                    state = state,
                    onCopyLanIpClicked = {
                        // TODO copy ip
                    },
                    onCopyWanIpClicked = {
                        // TODO copy ip
                    },
                    onLocationClicked = onLocationClicked,
                )
            }
        }
    }
}

@Composable
private fun ReadyContent(
    state: NetworkInfoViewModel.State.Ready,
    onCopyLanIpClicked: () -> Unit,
    onCopyWanIpClicked: () -> Unit,
    onLocationClicked: () -> Unit,
) {
    Column {

        LanCard(
            state = state.lan,
            onCopyLanIpClicked = onCopyLanIpClicked,
        )

        WanCard(
            state = state.wan,
            onCopyWanIpClicked = onCopyWanIpClicked,
            onLocationClicked = onLocationClicked,
        )
    }
}

@A11yPreview
@Composable
private fun NetworkInfoScreenPreview() = ThemedPreview {
    NetworkInfoScreen(
        state = NetworkInfoViewModel.State.Ready(
            lan = NetworkInfoViewModel.LanState.Ready(
                ipAddress = "192.168.0.1",
            ),
            wan = NetworkInfoViewModel.WanState.Ready(
                ipAddress = "109.123.654.321",
                locationText = "New York NY, US"
            ),
        ),
        onLocationClicked = { },
    )
}