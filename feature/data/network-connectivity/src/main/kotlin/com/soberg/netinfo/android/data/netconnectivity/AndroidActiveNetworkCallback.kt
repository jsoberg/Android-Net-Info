package com.soberg.netinfo.android.data.netconnectivity

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkCapabilities.NET_CAPABILITY_VALIDATED
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_VPN
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import com.soberg.netinfo.android.data.netconnectivity.local.FindLocalIpAddressUseCase
import com.soberg.netinfo.base.logging.Logger
import com.soberg.netinfo.base.type.network.NetworkInterface
import com.soberg.netinfo.domain.lan.NetworkConnectionRepository
import kotlinx.coroutines.channels.ProducerScope

internal class AndroidActiveNetworkCallback(
    private val scope: ProducerScope<NetworkConnectionRepository.State>,
    private val connectivityManager: ConnectivityManager,
    private val findLocalIpAddress: FindLocalIpAddressUseCase,
) : ConnectivityManager.NetworkCallback() {

    companion object {
        private const val Tag = "AndroidActiveNetworkCallback"
    }

    override fun onAvailable(network: Network) {
        emitActiveNetworkInformation()
    }

    override fun onCapabilitiesChanged(
        network: Network,
        networkCapabilities: NetworkCapabilities
    ) {
        emitActiveNetworkInformation()
    }

    override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
        emitActiveNetworkInformation()
    }

    override fun onLost(network: Network) {
        emitActiveNetworkInformation()
    }

    override fun onUnavailable() {
        emitActiveNetworkInformation()
    }

    private fun emitActiveNetworkInformation() = with(scope) {
        val activeNetwork = connectivityManager.activeNetwork
        if (activeNetwork == null) {
            Logger.debug(Tag, "Active network update to None")
            trySend(NetworkConnectionRepository.State.NoActiveConnection)
        } else {
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            val type = capabilities?.let(::interfaceType) ?: NetworkInterface.Type.Unknown
            val linkProperties = connectivityManager.getLinkProperties(activeNetwork)

            val interfaceName = linkProperties?.interfaceName
            Logger.debug(Tag, "Active network update to $interfaceName, type $type")
            val netInterface = NetworkInterface(
                name = interfaceName,
                ipAddress = interfaceName?.let(findLocalIpAddress::invoke),
                type = type,
                properties = capabilities?.let(::buildPropertiesList) ?: emptyList()
            )
            trySend(NetworkConnectionRepository.State.Connected(netInterface))
        }
    }

    private fun buildPropertiesList(
        capabilities: NetworkCapabilities
    ) = buildList {
        if (capabilities.hasCapability(NET_CAPABILITY_INTERNET) &&
            capabilities.hasCapability(NET_CAPABILITY_VALIDATED)
        ) {
            add(NetworkInterface.Properties.Internet)
        }
        if (capabilities.hasTransport(TRANSPORT_VPN)) {
            add(NetworkInterface.Properties.VPN)
        }
    }

    private fun interfaceType(
        capabilities: NetworkCapabilities
    ): NetworkInterface.Type = with(capabilities) {
        when {
            hasTransport(TRANSPORT_CELLULAR) -> NetworkInterface.Type.Cellular
            hasTransport(TRANSPORT_ETHERNET) -> NetworkInterface.Type.Ethernet
            hasTransport(TRANSPORT_WIFI) -> NetworkInterface.Type.Wifi
            else -> NetworkInterface.Type.Unknown
        }
    }

    fun emitState() {
        emitActiveNetworkInformation()
    }
}

internal fun ProducerScope<NetworkConnectionRepository.State>.activeNetworkCallback(
    connectivityManager: ConnectivityManager,
    findLocalIpAddress: FindLocalIpAddressUseCase,
) = AndroidActiveNetworkCallback(
    scope = this,
    connectivityManager = connectivityManager,
    findLocalIpAddress = findLocalIpAddress,
)