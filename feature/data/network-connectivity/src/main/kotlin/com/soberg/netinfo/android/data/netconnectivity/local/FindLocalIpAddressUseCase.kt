package com.soberg.netinfo.android.data.netconnectivity.local

import com.soberg.netinfo.base.type.network.IpAddress
import java.net.InetAddress
import java.net.NetworkInterface
import javax.inject.Inject

internal class FindLocalIpAddressUseCase @Inject constructor(
    private val getNetworkInterfaceByName: GetNetworkInterfaceByNameUseCase,
) {

    /** @return The local IP address with the name [interfaceName] if one exists, null otherwise. */
    operator fun invoke(interfaceName: String): IpAddress? {
        val networkInterface = getNetworkInterfaceByName(interfaceName) ?: return null
        // Try ipv4 first, then try ipv6.
        return tryFindIpAddress(networkInterface, preferIpv4 = true)
            ?: return tryFindIpAddress(networkInterface, preferIpv4 = false)
    }

    private fun tryFindIpAddress(
        networkInterface: NetworkInterface,
        preferIpv4: Boolean,
    ): IpAddress? {
        for (address in networkInterface.inetAddresses) {
            if (!address.isLoopbackAddress) {
                val parsedAddress = parseIpAddress(address, preferIpv4)
                if (parsedAddress != null) {
                    return parsedAddress
                }
            }
        }
        return null
    }

    private fun parseIpAddress(address: InetAddress, preferIpv4: Boolean): IpAddress? {
        val hostAddress = address.hostAddress ?: return null

        val isIPv4 = hostAddress.indexOf(':') == -1
        return when {
            preferIpv4 && isIPv4 -> IpAddress(hostAddress)

            !preferIpv4 && !isIPv4 -> {
                // Drop ip6 zone suffix (e.g %wlan0)
                val delimited = hostAddress.indexOf('%')
                val ipStringRep = if (delimited == -1) {
                    hostAddress
                } else {
                    hostAddress.substring(0, delimited)
                }
                IpAddress(ipStringRep)
            }

            else -> null
        }
    }
}

internal interface GetNetworkInterfaceByNameUseCase {
    operator fun invoke(interfaceName: String): NetworkInterface?
}