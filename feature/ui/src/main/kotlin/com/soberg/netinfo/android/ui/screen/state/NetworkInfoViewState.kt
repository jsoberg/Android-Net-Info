package com.soberg.netinfo.android.ui.screen.state

import androidx.compose.runtime.Immutable
import com.soberg.netinfo.android.ui.screen.state.NetworkInfoViewState.Ready.Lan.ConnectionType.NoConnection

@Immutable
sealed interface NetworkInfoViewState {
    data object Loading : NetworkInfoViewState

    data object NoConnectionsFound : NetworkInfoViewState

    data class Ready(
        val lan: Lan,
        val wan: Wan,
    ) : NetworkInfoViewState {

        @Immutable
        sealed interface Lan {
            val type: ConnectionType

            data object Unknown : Lan {
                override val type: ConnectionType = NoConnection
            }

            data class Connected(
                val ipAddress: String,
                override val type: ConnectionType,
            ) : Lan

            enum class ConnectionType {
                NoConnection,
                Cellular,
                Ethernet,
                Wifi,
            }
        }

        @Immutable
        sealed interface Wan {
            data object CannotConnect : Wan

            data class Connected(
                val ipAddress: String,
                val locationText: String?,
            ) : Wan
        }
    }
}