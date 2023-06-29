package com.soberg.netinfo.android.ui.screen

import androidx.annotation.MainThread
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.soberg.netinfo.android.infra.android.services.clipboard.CopyToClipboardUseCase
import com.soberg.netinfo.android.infra.android.services.intent.LaunchMapsIntentUseCase
import com.soberg.netinfo.android.infra.viewmodel.ext.asStateFlow
import com.soberg.netinfo.android.ui.R
import com.soberg.netinfo.base.type.geodetic.GeodeticInformation
import com.soberg.netinfo.base.type.network.NetworkInterface
import com.soberg.netinfo.domain.lan.NetworkConnectionRepository
import com.soberg.netinfo.domain.wan.WanInfoRepository
import com.soberg.netinfo.domain.wan.model.WanInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class NetworkInfoViewModel @Inject internal constructor(
    private val connectionRepository: NetworkConnectionRepository,
    private val wanInfoRepository: WanInfoRepository,
    private val launchMapsIntent: LaunchMapsIntentUseCase,
    private val copyToClipboard: CopyToClipboardUseCase,
) : ViewModel() {

    private val dataCache = DataCache()

    val stateFlow: StateFlow<State> = asStateFlow(initialValue = State.Loading) {
        connectionRepository.activeConnectionStateFlow
            .map(::queryViewState)
    }

    private suspend fun queryViewState(state: NetworkConnectionRepository.State): State =
        when (state) {
            is NetworkConnectionRepository.State.NoActiveConnection -> {
                dataCache.clear()
                State.NoConnectionsFound
            }

            is NetworkConnectionRepository.State.Connected -> {
                dataCache.updateLan(state.netInterface)
                val result = wanInfoRepository.loadWanInfo()
                State.Ready(
                    lan = toLanState(state.netInterface),
                    wan = toWanState(
                        wanResult = result,
                    ),
                )
            }
        }

    private fun toLanState(
        netInterface: NetworkInterface,
    ): LanState =
        when (netInterface.ipAddress) {
            null -> LanState.Unknown
            else -> LanState.Ready(
                ipAddress = netInterface.ipAddress!!.value,
            )
        }

    private fun toWanState(
        wanResult: WanInfoRepository.Result,
    ): WanState =
        when (wanResult) {
            is WanInfoRepository.Result.Error -> WanState.CannotConnect
            is WanInfoRepository.Result.Success -> {
                dataCache.updateWan(wanResult.wanInfo)
                WanState.Ready(
                    ipAddress = wanResult.wanInfo.ip.toString(),
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
            labelResId = R.string.wan_ip_copy_label,
            valueToCopy = wanInfo.ip.value,
        )
    }

    fun copyLanIpAddress() = ifLanInterfaceCached { iface ->
        iface.ipAddress?.let { ip ->
            copyToClipboard(
                labelResId = R.string.lan_ip_copy_label,
                valueToCopy = ip.value,
            )
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

    @Immutable
    sealed interface State {
        object Loading : State

        object NoConnectionsFound : State

        data class Ready(
            val lan: LanState,
            val wan: WanState,
        ) : State
    }

    @Immutable
    sealed interface LanState {
        object Unknown : LanState

        data class Ready(
            val ipAddress: String,
        ) : LanState
    }

    @Immutable
    sealed interface WanState {
        object CannotConnect : WanState

        data class Ready(
            val ipAddress: String,
            val locationText: String?,
        ) : WanState
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