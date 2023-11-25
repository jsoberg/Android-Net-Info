package com.soberg.netinfo.base.type.network

object NetworkInterfaceTestFixtures {
    fun get(
        name: String? = "eth0",
        ipAddress: IpAddress? = IpAddress("192.168.0.2"),
        type: NetworkInterface.Type = NetworkInterface.Type.Ethernet,
        properties: List<NetworkInterface.Properties> = listOf(NetworkInterface.Properties.Internet),
    ) = NetworkInterface(
        name = name,
        ipAddress = ipAddress,
        type = type,
        properties = properties,
    )
}