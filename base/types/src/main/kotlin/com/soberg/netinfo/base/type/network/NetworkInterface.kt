package com.soberg.netinfo.base.type.network

data class NetworkInterface(
    /** System name of this network interface (i.e. wlan0, eth0, etc). */
    val name: String?,
    /** Describes this interface's network type. */
    val type: Type,
    /** Properties that this interface has. */
    val properties: List<Properties>,
) {
    enum class Properties {
        /** Indicates that this network interface is connected through a VPN (Virtual Private Network). */
        VPN,

        /** Indicates that this network interface is connected to the greater Internet. */
        Internet,
    }

    /** Describes the type of network that this interface represents. */
    enum class Type {
        Cellular,
        Ethernet,
        Wifi,

        Unknown,
    }
}
