package com.soberg.netinfo.android.ui.screen.state

import com.soberg.netinfo.base.type.network.NetworkInterface

internal fun NetworkInterface.Type.toViewState(): NetworkInfoViewState.Ready.Lan.ConnectionType =
    when (this) {
        NetworkInterface.Type.Wifi -> NetworkInfoViewState.Ready.Lan.ConnectionType.Wifi
        NetworkInterface.Type.Cellular -> NetworkInfoViewState.Ready.Lan.ConnectionType.Cellular
        NetworkInterface.Type.Ethernet -> NetworkInfoViewState.Ready.Lan.ConnectionType.Ethernet
        NetworkInterface.Type.Unknown -> NetworkInfoViewState.Ready.Lan.ConnectionType.NoConnection
    }