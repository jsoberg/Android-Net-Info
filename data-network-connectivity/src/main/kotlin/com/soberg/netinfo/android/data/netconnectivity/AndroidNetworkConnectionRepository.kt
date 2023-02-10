package com.soberg.netinfo.android.data.netconnectivity

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.soberg.netinfo.base.annotation.ApplicationCoroutineScope
import com.soberg.netinfo.domain.lan.NetworkConnectionRepository
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.plus
import javax.inject.Inject

internal class AndroidNetworkConnectionRepository @Inject constructor(
    @ApplicationCoroutineScope appCoroutineScope: CoroutineScope,
    connectivityManager: ConnectivityManager,
) : NetworkConnectionRepository {

    override val activeConnectionStateFlow: Flow<NetworkConnectionRepository.State> =
        createActiveConnectionFlow(connectivityManager)
            .shareIn(
                scope = appCoroutineScope + CoroutineName("AndroidNetworkConnectionRepository"),
                started = WhileSubscribed(),
                replay = 1,
            )

    private val request = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_VPN)
        .build()

    private fun createActiveConnectionFlow(connectivityManager: ConnectivityManager) =
        callbackFlow {
            // TODO Handle not having permissions granted.
            val callback = activeNetworkCallback(connectivityManager)
            connectivityManager.registerNetworkCallback(request, callback)
            awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
        }
}