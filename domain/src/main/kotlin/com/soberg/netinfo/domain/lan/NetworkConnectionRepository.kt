package com.soberg.netinfo.domain.lan

import com.soberg.netinfo.base.type.network.NetworkInterface
import kotlinx.coroutines.flow.Flow

interface NetworkConnectionRepository {

    val activeConnectionStateFlow: Flow<State>

    sealed interface State {
        object NotConnected : State

        data class Connected(
            val netInterface: NetworkInterface,
        ) : State
    }
}