package com.soberg.netinfo.android.data.netconnectivity

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_VPN
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import com.soberg.netinfo.base.type.network.NetworkInterface
import com.soberg.netinfo.domain.lan.NetworkConnectionRepository
import kotlinx.coroutines.channels.ProducerScope

internal class AndroidActiveNetworkCallback constructor(
    private val scope: ProducerScope<NetworkConnectionRepository.State>,
    private val connectivityManager: ConnectivityManager,
) : ConnectivityManager.NetworkCallback() {

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
            trySend(NetworkConnectionRepository.State.NoActiveConnection)
        } else {
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            val linkProperties = connectivityManager.getLinkProperties(activeNetwork)

            val netInterface = NetworkInterface(
                name = linkProperties?.interfaceName,
                type = capabilities?.let(::interfaceType) ?: NetworkInterface.Type.Unknown,
                properties = capabilities?.let(::buildPropertiesList) ?: emptyList()
            )
            trySend(NetworkConnectionRepository.State.Connected(netInterface))
        }
    }

    private fun buildPropertiesList(
        capabilities: NetworkCapabilities
    ) = buildList {
        if (capabilities.hasCapability(NET_CAPABILITY_INTERNET)) {
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
) = AndroidActiveNetworkCallback(this, connectivityManager)