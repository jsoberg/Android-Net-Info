package com.soberg.netinfo.android.ui

import androidx.lifecycle.ViewModel
import com.soberg.netinfo.android.infra.viewmodel.ext.asStateFlow
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
                toViewState(result)
            }
        }

    private fun toViewState(result: WanInfoRepository.Result): State =
        when (result) {
            is WanInfoRepository.Result.Error -> State.CannotConnect
            is WanInfoRepository.Result.Success -> State.Connected(
                wanIpAddressString = result.wanInfo.ip.toString()
            )
        }

    sealed interface State {
        object Loading : State
        object CannotConnect : State

        data class Connected(
            val wanIpAddressString: String,
        ) : State
    }
}