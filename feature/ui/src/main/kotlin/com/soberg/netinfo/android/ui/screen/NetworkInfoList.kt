package com.soberg.netinfo.android.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.soberg.netinfo.android.ui.core.pulltorefresh.PullToRefreshContainer
import com.soberg.netinfo.android.ui.screen.state.NetworkInfoViewState
import com.soberg.netinfo.android.ui.screen.state.toDrawableResId
import com.soberg.netinfo.android.ui.screen.state.toTextStringResId
import com.soberg.netinfo.feature.resources.drawables.R as DrawablesR
import com.soberg.netinfo.feature.resources.strings.R as StringsR

@Composable
internal fun NetworkInfoList(
    state: NetworkInfoViewState.Ready,
    onRefreshStarted: () -> Unit,
    onCopyLanIpClicked: () -> Unit,
    onCopyWanIpClicked: () -> Unit,
    onLocationClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    PullToRefreshContainer(
        onRefreshStarted = onRefreshStarted,
        modifier = modifier,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            when (val lanState = state.lan) {
                is NetworkInfoViewState.Ready.Lan.Connected -> {
                    connectedLanListItems(
                        state = lanState,
                        onCopyLanIpClicked = onCopyLanIpClicked,
                    )

                    wanListItems(
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
}

private fun LazyListScope.connectedLanListItems(
    state: NetworkInfoViewState.Ready.Lan.Connected,
    onCopyLanIpClicked: () -> Unit,
) {
    item {
        NetworkListItem(
            titleText = stringResource(StringsR.string.connection_type_content_header),
            contentText = stringResource(state.type.toTextStringResId()),
            iconDrawableRes = state.type.toDrawableResId(),
        )
    }

    val connectedToVpnContentTextRes = if (state.isConnectedToVpn) {
        StringsR.string.vpn_connection_active
    } else {
        StringsR.string.vpn_connection_inactive
    }

    val connectedToVpnIconRes = if (state.isConnectedToVpn) {
        DrawablesR.drawable.ic_vpn_lock
    } else {
        DrawablesR.drawable.ic_lock_open
    }

    item {
        NetworkListItem(
            titleText = stringResource(StringsR.string.connected_to_vpn_content_header),
            contentText = stringResource(id = connectedToVpnContentTextRes),
            iconDrawableRes = connectedToVpnIconRes,
        )
    }

    item {
        NetworkListItem(
            modifier = Modifier
                .clickable(onClick = onCopyLanIpClicked),
            titleText = stringResource(StringsR.string.local_ip_content_header),
            contentText = state.ipAddress,
            iconDrawableRes = com.soberg.netinfo.feature.resources.drawables.R.drawable.ic_content_copy,
        )
    }
}

private fun LazyListScope.wanListItems(
    state: NetworkInfoViewState.Ready.Wan,
    onCopyWanIpClicked: () -> Unit,
    onLocationClicked: () -> Unit,
) {
    when (state) {
        is NetworkInfoViewState.Ready.Wan.Connected -> {
            connectedWanListItems(
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

private fun LazyListScope.connectedWanListItems(
    state: NetworkInfoViewState.Ready.Wan.Connected,
    onCopyWanIpClicked: () -> Unit,
    onLocationClicked: () -> Unit,
) {
    item {
        NetworkListItem(
            modifier = Modifier
                .clickable(onClick = onCopyWanIpClicked),
            titleText = stringResource(StringsR.string.external_ip_content_header),
            contentText = state.ipAddress,
            iconDrawableRes = DrawablesR.drawable.ic_content_copy,
        )
    }

    state.locationText?.let { locationText ->
        item {
            NetworkListItem(
                modifier = Modifier
                    .clickable(onClick = onLocationClicked),
                titleText = stringResource(StringsR.string.ip_geolocation_content_header),
                contentText = locationText,
                iconDrawableRes = DrawablesR.drawable.ic_my_location,
            )
        }
    }
}