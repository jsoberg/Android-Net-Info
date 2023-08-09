package com.soberg.netinfo.android.ui.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.soberg.netinfo.android.infra.compose.ext.event.collectComposableEventFlow
import com.soberg.netinfo.android.ui.core.preview.A11yPreview
import com.soberg.netinfo.android.ui.core.preview.ThemedPreview
import com.soberg.netinfo.android.ui.core.theme.Dimens
import com.soberg.netinfo.android.ui.screen.NetworkInfoViewModel.Event
import com.soberg.netinfo.android.ui.screen.card.LanContent
import com.soberg.netinfo.android.ui.screen.card.WanContent
import com.soberg.netinfo.android.ui.screen.state.NetworkInfoViewState
import com.soberg.netinfo.feature.resources.strings.R as StringsR

@Composable
fun NetworkInfoScreen(
    viewModel: NetworkInfoViewModel,
) {
    val context = LocalContext.current
    viewModel.eventFlow.collectComposableEventFlow { event ->
        handleEvent(
            context = context,
            event = event,
        )
    }

    val state by viewModel.stateFlow.collectAsState()
    NetworkInfoScreen(
        state = state,
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
            Toast.makeText(context, StringsR.string.wan_ip_copied_message, Toast.LENGTH_SHORT)
                .show()
        }

        is Event.LanIpCopySuccess -> {
            Toast.makeText(context, StringsR.string.lan_ip_copied_message, Toast.LENGTH_SHORT)
                .show()
        }
    }
}

@Composable
private fun NetworkInfoScreen(
    state: NetworkInfoViewState,
    onCopyLanIpClicked: () -> Unit,
    onCopyWanIpClicked: () -> Unit,
    onLocationClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        when (state) {
            is NetworkInfoViewState.NoConnectionsFound,
            is NetworkInfoViewState.Loading -> {
                // TODO Add Loading
            }

            is NetworkInfoViewState.Ready -> {
                ReadyContent(
                    state = state,
                    onCopyLanIpClicked = onCopyLanIpClicked,
                    onCopyWanIpClicked = onCopyWanIpClicked,
                    onLocationClicked = onLocationClicked,
                )
            }
        }
    }
}

@Composable
private fun ReadyContent(
    state: NetworkInfoViewState.Ready,
    onCopyLanIpClicked: () -> Unit,
    onCopyWanIpClicked: () -> Unit,
    onLocationClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(
                horizontal = Dimens.Padding.Base100,
                vertical = Dimens.Padding.Base100,
            ),
        verticalArrangement = Arrangement.spacedBy(Dimens.Padding.Base100),
    ) {

        LanContent(
            state = state.lan,
            onCopyLanIpClicked = onCopyLanIpClicked,
        )

        WanContent(
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
        state = NetworkInfoViewState.Ready(
            lan = NetworkInfoViewState.Ready.Lan.Connected(
                ipAddress = "192.168.0.1",
                type = NetworkInfoViewState.Ready.Lan.ConnectionType.Cellular,
            ),
            wan = NetworkInfoViewState.Ready.Wan.Connected(
                ipAddress = "109.123.654.321",
                locationText = "New York NY, US"
            ),
        ),
        onCopyLanIpClicked = { },
        onCopyWanIpClicked = { },
        onLocationClicked = { },
    )
}