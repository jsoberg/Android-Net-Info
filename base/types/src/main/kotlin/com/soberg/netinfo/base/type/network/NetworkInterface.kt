package com.soberg.netinfo.base.type.network

data class NetworkInterface(
    /** System name of this network interface (i.e. wlan0, eth0, etc). */
    val name: String?,
    /** Local IP Address for this interface (ipv4 if available, ipv6 or null if not). */
    val ipAddress: IpAddress?,
    /** Describes this interface's network type. */
    val type: Type,
    /** Properties that this interface has. */
    val properties: List<Properties>,
) {

    val isConnectedToVpn: Boolean
        get() = properties.contains(Properties.VPN)

    val canConnectToInternet: Boolean
        get() = properties.contains(Properties.Internet)

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
