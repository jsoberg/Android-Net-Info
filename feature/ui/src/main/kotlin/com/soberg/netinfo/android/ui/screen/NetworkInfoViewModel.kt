package com.soberg.netinfo.android.ui.screen

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.soberg.netinfo.android.infra.viewmodel.ext.asStateFlow
import com.soberg.netinfo.base.type.geodetic.GeodeticInformation
import com.soberg.netinfo.base.type.network.NetworkInterface
import com.soberg.netinfo.domain.lan.NetworkConnectionRepository
import com.soberg.netinfo.domain.wan.WanInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class NetworkInfoViewModel @Inject constructor(
    private val connectionRepository: NetworkConnectionRepository,
    private val wanInfoRepository: WanInfoRepository,
) : ViewModel() {

    val stateFlow: StateFlow<State> = asStateFlow(initialValue = State.Loading) {
        connectionRepository.activeConnectionStateFlow
            .map(::queryViewState)
    }

    private suspend fun queryViewState(state: NetworkConnectionRepository.State): State =
        when (state) {
            is NetworkConnectionRepository.State.NoActiveConnection ->
                State.NoConnectionsFound

            is NetworkConnectionRepository.State.Connected -> {
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
            is WanInfoRepository.Result.Success -> WanState.Ready(
                ipAddress = wanResult.wanInfo.ip.toString(),
                locationText = wanResult.wanInfo.ispGeoInfo?.locationDisplayText()
            )
        }

    private fun GeodeticInformation.locationDisplayText(): String =
        "$cityName ${region.code}, ${country.iso}"

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
}