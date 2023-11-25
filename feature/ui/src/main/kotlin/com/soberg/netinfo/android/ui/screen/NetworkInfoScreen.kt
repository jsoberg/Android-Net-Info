package com.soberg.netinfo.android.ui.screen

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.soberg.netinfo.android.infra.compose.ext.event.CollectComposableEventFlow
import com.soberg.netinfo.android.ui.core.preview.A11yPreview
import com.soberg.netinfo.android.ui.core.preview.ThemedPreview
import com.soberg.netinfo.android.ui.screen.NetworkInfoViewModel.Event
import com.soberg.netinfo.android.ui.screen.state.NetworkInfoViewState
import com.soberg.netinfo.android.ui.screen.state.toDrawableResId
import com.soberg.netinfo.android.ui.screen.state.toTextStringResId
import com.soberg.netinfo.feature.resources.drawables.R
import com.soberg.netinfo.feature.resources.strings.R as StringsR

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
    onCopyLanIpClicked: () -> Unit,
    onCopyWanIpClicked: () -> Unit,
    onLocationClicked: () -> Unit,
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

@Composable
private fun ReadyContent(
    state: NetworkInfoViewState.Ready,
    onCopyLanIpClicked: () -> Unit,
    onCopyWanIpClicked: () -> Unit,
    onLocationClicked: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        when (state.lan) {
            is NetworkInfoViewState.Ready.Lan.Connected -> {
                LanReadyContent(
                    state = state.lan,
                    onCopyLanIpClicked = onCopyLanIpClicked,
                )

                WanContent(
                    state = state.wan,
                    onCopyWanIpClicked = onCopyWanIpClicked,
                    onLocationClicked = onLocationClicked,
                )
            }

            is NetworkInfoViewState.Ready.Lan.Unknown -> {
                // TODO: Show error/unknown state
            }
        }
    }
}

@Composable
private fun LanReadyContent(
    state: NetworkInfoViewState.Ready.Lan.Connected,
    onCopyLanIpClicked: () -> Unit,
) {
    NetworkListItem(
        titleText = stringResource(StringsR.string.connection_type_content_header),
        contentText = stringResource(state.type.toTextStringResId()),
        iconDrawableRes = state.type.toDrawableResId(),
    )

    NetworkListItem(
        modifier = Modifier
            .clickable(onClick = onCopyLanIpClicked),
        titleText = stringResource(StringsR.string.local_ip_content_header),
        contentText = state.ipAddress,
        iconDrawableRes = R.drawable.ic_content_copy,
    )
}

@Composable
internal fun WanContent(
    state: NetworkInfoViewState.Ready.Wan,
    onCopyWanIpClicked: () -> Unit,
    onLocationClicked: () -> Unit,
) {
    when (state) {
        is NetworkInfoViewState.Ready.Wan.Connected -> {
            WanReadyContent(
                state = state,
                onCopyWanIpClicked = onCopyWanIpClicked,
                onLocationClicked = onLocationClicked,
            )
        }

        is NetworkInfoViewState.Ready.Wan.CannotConnect
        -> {
            // TODO: Show can't connect content
        }
    }
}

@Composable
private fun WanReadyContent(
    state: NetworkInfoViewState.Ready.Wan.Connected,
    onCopyWanIpClicked: () -> Unit,
    onLocationClicked: () -> Unit,
) {
    NetworkListItem(
        modifier = Modifier
            .clickable(onClick = onCopyWanIpClicked),
        titleText = stringResource(StringsR.string.external_ip_content_header),
        contentText = state.ipAddress,
        iconDrawableRes = R.drawable.ic_content_copy,
    )

    state.locationText?.let { locationText ->
        NetworkListItem(
            modifier = Modifier
                .clickable(onClick = onLocationClicked),
            titleText = stringResource(StringsR.string.ip_geolocation_content_header),
            contentText = locationText,
            iconDrawableRes = R.drawable.ic_my_location,
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