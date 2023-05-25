package com.soberg.netinfo.android.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.soberg.netinfo.android.infra.viewmodel.ext.asStateFlow
import com.soberg.netinfo.android.ui.screen.state.toDrawableRes
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
            is NetworkConnectionRepository.State.NotConnected -> {
                State.CannotConnect
            }

            is NetworkConnectionRepository.State.Connected -> {
                val result = wanInfoRepository.loadWanInfo()
                toViewState(
                    localInterface = state.netInterface,
                    wanResult = result,
                )
            }
        }

    private fun toViewState(
        localInterface: NetworkInterface,
        wanResult: WanInfoRepository.Result,
    ): State =
        when (wanResult) {
            is WanInfoRepository.Result.Error -> State.CannotConnect
            is WanInfoRepository.Result.Success -> State.Connected(
                wanIpAddress = wanResult.wanInfo.ip.toString(),
                networkTypeDrawableRes = localInterface.type.toDrawableRes(),
            )
        }

    @Immutable
    sealed interface State {
        object Loading : State

        object CannotConnect : State

        data class Connected(
            val wanIpAddress: String,
            @DrawableRes
            val networkTypeDrawableRes: Int,
        ) : State
    }
}