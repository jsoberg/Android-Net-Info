package com.soberg.netinfo.android.ui.screen.state

import androidx.annotation.DrawableRes
import com.soberg.netinfo.base.type.network.NetworkInterface
import com.soberg.netinfo.feature.resources.drawables.R as DrawablesR

@DrawableRes
internal fun NetworkInterface.Type.toDrawableRes(): Int =
    when (this) {
        NetworkInterface.Type.Wifi -> DrawablesR.drawable.ic_wifi
        NetworkInterface.Type.Cellular -> DrawablesR.drawable.ic_signal_cellular_alt
        NetworkInterface.Type.Ethernet -> DrawablesR.drawable.ic_settings_ethernet
        // TODO Determine what to return here
        NetworkInterface.Type.Unknown -> DrawablesR.drawable.ic_wifi
    }