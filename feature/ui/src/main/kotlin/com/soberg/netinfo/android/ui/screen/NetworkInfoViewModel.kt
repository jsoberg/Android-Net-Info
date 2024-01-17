package com.soberg.netinfo.android.ui.screen

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import com.soberg.netinfo.android.infra.android.services.clipboard.CopyToClipboardUseCase
import com.soberg.netinfo.android.infra.android.services.intent.LaunchMapsIntentUseCase
import com.soberg.netinfo.android.infra.viewmodel.ext.asStateFlow
import com.soberg.netinfo.android.ui.screen.state.NetworkInfoViewState
import com.soberg.netinfo.android.ui.screen.state.toViewState
import com.soberg.netinfo.base.type.geodetic.GeodeticInformation
import com.soberg.netinfo.base.type.network.NetworkInterface
import com.soberg.netinfo.domain.lan.NetworkConnectionRepository
import com.soberg.netinfo.domain.wan.WanInfoRepository
import com.soberg.netinfo.domain.wan.model.WanInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import com.soberg.netinfo.feature.resources.strings.R as StringsR

@HiltViewModel
class NetworkInfoViewModel @Inject internal constructor(
    private val connectionRepository: NetworkConnectionRepository,
    private val wanInfoRepository: WanInfoRepository,
    private val launchMapsIntent: LaunchMapsIntentUseCase,
    private val copyToClipboard: CopyToClipboardUseCase,
) : ViewModel() {

    private val dataCache = DataCache()

    val stateFlow: StateFlow<NetworkInfoViewState> =
        asStateFlow(initialValue = NetworkInfoViewState.Loading) {
            connectionRepository.activeConnectionStateFlow
                .map(::queryViewState)
        }

    private val eventChannel = Channel<Event>()
    val eventFlow = eventChannel.receiveAsFlow()

    private suspend fun queryViewState(state: NetworkConnectionRepository.State): NetworkInfoViewState =
        when (state) {
            is NetworkConnectionRepository.State.NoActiveConnection -> {
                dataCache.clear()
                NetworkInfoViewState.NoConnectionsFound
            }

            is NetworkConnectionRepository.State.Connected -> {
                dataCache.updateLan(state.netInterface)
                val result = wanInfoRepository.loadWanInfo()
                NetworkInfoViewState.Ready(
                    lan = toLanState(state.netInterface),
                    wan = toWanState(
                        wanResult = result,
                    ),
                )
            }
        }

    private fun toLanState(
        netInterface: NetworkInterface,
    ): NetworkInfoViewState.Ready.Lan =
        when (val ip = netInterface.ipAddress) {
            null -> NetworkInfoViewState.Ready.Lan.Unknown
            else -> NetworkInfoViewState.Ready.Lan.Connected(
                ipAddress = ip.value,
                type = netInterface.type.toViewState(),
                isConnectedToVpn = netInterface.isConnectedToVpn,
            )
        }

    private fun toWanState(
        wanResult: WanInfoRepository.Result,
    ): NetworkInfoViewState.Ready.Wan =
        when (wanResult) {
            is WanInfoRepository.Result.Error -> NetworkInfoViewState.Ready.Wan.CannotConnect
            is WanInfoRepository.Result.Success -> {
                dataCache.updateWan(wanResult.wanInfo)
                NetworkInfoViewState.Ready.Wan.Connected(
                    ipAddress = wanResult.wanInfo.ip.value,
                    locationText = wanResult.wanInfo.ispGeoInfo?.locationDisplayText(),
                )
            }
        }

    private fun GeodeticInformation.locationDisplayText(): String =
        "$cityName ${region.code}, ${country.iso}"

    fun launchMapsAtLocation() = ifWanInfoCached { wanInfo ->
        wanInfo.ispGeoInfo?.let { geo ->
            launchMapsIntent(geo.location)
        }
    }

    fun copyWanIpAddress() = ifWanInfoCached { wanInfo ->
        copyToClipboard(
            labelResId = StringsR.string.external_ip_content_header,
            valueToCopy = wanInfo.ip.value,
        )
        eventChannel.trySend(Event.WanIpCopySuccess)
    }

    fun copyLanIpAddress() = ifLanInterfaceCached { iface ->
        iface.ipAddress?.let { ip ->
            copyToClipboard(
                labelResId = StringsR.string.local_ip_content_header,
                valueToCopy = ip.value,
            )
            eventChannel.trySend(Event.LanIpCopySuccess)
        }
    }

    private inline fun ifWanInfoCached(crossinline block: (WanInfo) -> Unit) {
        dataCache.cachedWanInfo?.let { wanInfo ->
            block(wanInfo)
        }
    }

    private inline fun ifLanInterfaceCached(crossinline block: (NetworkInterface) -> Unit) {
        dataCache.cachedLanInterface?.let { iface ->
            block(iface)
        }
    }

    sealed interface Event {
        data object LanIpCopySuccess : Event
        data object WanIpCopySuccess : Event
    }

    @MainThread
    private class DataCache {

        var cachedLanInterface: NetworkInterface? = null
            private set
        var cachedWanInfo: WanInfo? = null
            private set

        fun updateLan(iface: NetworkInterface) {
            cachedLanInterface = iface
        }

        fun updateWan(info: WanInfo) {
            cachedWanInfo = info
        }

        fun clear() {
            cachedLanInterface = null
            cachedWanInfo = null
        }
    }
}