package com.soberg.netinfo.domain.lan

import com.soberg.netinfo.base.type.network.NetworkInterface
import kotlinx.coroutines.flow.Flow

interface NetworkConnectionRepository {

    val activeConnectionStateFlow: Flow<State>

    fun restart()

    sealed interface State {
        data object NoActiveConnection : State

        data class Connected(
            val netInterface: NetworkInterface,
        ) : State
    }
}