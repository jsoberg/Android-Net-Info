package com.soberg.netinfo.android.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.soberg.netinfo.android.infra.compose.ext.event.CollectComposableEventFlow
import com.soberg.netinfo.android.ui.core.preview.A11yPreview
import com.soberg.netinfo.android.ui.core.preview.ThemedPreview
import com.soberg.netinfo.android.ui.screen.NetworkInfoViewModel.Event
import com.soberg.netinfo.android.ui.screen.loading.NetworkInfoLoading
import com.soberg.netinfo.android.ui.screen.state.NetworkInfoViewState

@Composable
fun NetworkInfoScreen(
    viewModel: NetworkInfoViewModel,
) {
    val context = LocalContext.current
    viewModel.eventFlow.CollectComposableEventFlow { event ->
        handleEvent(
            context = context,
            event = event,
        )
    }

    val state by viewModel.stateFlow.collectAsState()
    NetworkInfoScreen(
        state = state,
        onRefreshStarted = viewModel::pullToRefresh,
        onCopyLanIpClicked = viewModel::copyLanIpAddress,
        onCopyWanIpClicked = viewModel::copyWanIpAddress,
        onLocationClicked = viewModel::launchMapsAtLocation,
    )
}

private fun handleEvent(
    context: Context,
    event: Event,
) {
    when (event) {
        is Event.WanIpCopySuccess -> {
            // TODO Determine if we should show a toast or not confirming copy based on API
        }

        is Event.LanIpCopySuccess -> {
            // TODO Determine if we should show a toast or not confirming copy based on API
        }
    }
}

@Composable
private fun NetworkInfoScreen(
    state: NetworkInfoViewState,
    onRefreshStarted: () -> Unit,
    onCopyLanIpClicked: () -> Unit,
    onCopyWanIpClicked: () -> Unit,
    onLocationClicked: () -> Unit,
) {
    when (state) {
        is NetworkInfoViewState.NoConnectionsFound,
        is NetworkInfoViewState.Loading -> {
            NetworkInfoLoading(
                modifier = Modifier.fillMaxSize(),
            )
        }

        is NetworkInfoViewState.Ready -> {
            NetworkInfoList(
                state = state,
                onRefreshStarted = onRefreshStarted,
                onCopyLanIpClicked = onCopyLanIpClicked,
                onCopyWanIpClicked = onCopyWanIpClicked,
                onLocationClicked = onLocationClicked,
            )
        }
    }
}

@A11yPreview
@Composable
private fun NetworkInfoScreenPreview() = ThemedPreview {
    NetworkInfoScreen(
        state = NetworkInfoViewState.Ready(
            lan = NetworkInfoViewState.Ready.Lan.Connected(
                ipAddress = "192.168.0.1",
                type = NetworkInfoViewState.Ready.Lan.ConnectionType.Cellular,
                isConnectedToVpn = true,
            ),
            wan = NetworkInfoViewState.Ready.Wan.Connected(
                ipAddress = "109.123.654.321",
                locationText = "New York NY, US"
            ),
        ),
        onRefreshStarted = { },
        onCopyLanIpClicked = { },
        onCopyWanIpClicked = { },
        onLocationClicked = { },
    )
}