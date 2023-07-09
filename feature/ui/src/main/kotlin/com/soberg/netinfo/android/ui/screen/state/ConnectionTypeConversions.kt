package com.soberg.netinfo.android.ui.screen.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.soberg.netinfo.android.ui.screen.state.NetworkInfoViewState.Ready.Lan.ConnectionType.Cellular
import com.soberg.netinfo.android.ui.screen.state.NetworkInfoViewState.Ready.Lan.ConnectionType.Ethernet
import com.soberg.netinfo.android.ui.screen.state.NetworkInfoViewState.Ready.Lan.ConnectionType.NoConnection
import com.soberg.netinfo.android.ui.screen.state.NetworkInfoViewState.Ready.Lan.ConnectionType.Wifi
import com.soberg.netinfo.feature.resources.drawables.R as DrawablesR
import com.soberg.netinfo.feature.resources.strings.R as StringsR

@DrawableRes
internal fun NetworkInfoViewState.Ready.Lan.ConnectionType.toDrawableResId(): Int =
    when (this) {
        Wifi -> DrawablesR.drawable.ic_wifi
        Cellular -> DrawablesR.drawable.ic_signal_cellular_alt
        Ethernet -> DrawablesR.drawable.ic_settings_ethernet
        NoConnection -> DrawablesR.drawable.ic_device_unknown
    }

@StringRes
internal fun NetworkInfoViewState.Ready.Lan.ConnectionType.toTextStringResId(): Int =
    when (this) {
        Wifi -> StringsR.string.connection_type_wifi
        Cellular -> StringsR.string.connection_type_cellular
        Ethernet -> StringsR.string.connection_type_ethernet
        NoConnection -> StringsR.string.connection_type_no_connection
    }