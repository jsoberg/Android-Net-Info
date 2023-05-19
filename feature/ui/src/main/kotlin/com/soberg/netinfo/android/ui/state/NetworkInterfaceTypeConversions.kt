package com.soberg.netinfo.android.ui.state

import androidx.annotation.DrawableRes
import com.soberg.netinfo.android.ui.R
import com.soberg.netinfo.base.type.network.NetworkInterface

@DrawableRes
internal fun NetworkInterface.Type.toDrawableRes(): Int =
    when (this) {
        NetworkInterface.Type.Wifi -> R.drawable.ic_wifi
        NetworkInterface.Type.Cellular -> R.drawable.ic_signal_cellular_alt
        NetworkInterface.Type.Ethernet -> R.drawable.ic_settings_ethernet
        // TODO Determine what to return here
        NetworkInterface.Type.Unknown -> R.drawable.ic_wifi
    }