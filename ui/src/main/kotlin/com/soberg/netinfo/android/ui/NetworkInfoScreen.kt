package com.soberg.netinfo.android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.soberg.netinfo.android.ui.wan.WanInfoRow

@Composable
fun NetworkInfoScreen(
    viewModel: NetworkInfoViewModel,
) {
    val state by viewModel.stateFlow.collectAsState()
    NetworkInfoScreen(state)
}

@Composable
fun NetworkInfoScreen(
    state: NetworkInfoViewModel.State,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        when (state) {
            is NetworkInfoViewModel.State.CannotConnect,
            is NetworkInfoViewModel.State.Loading -> {
            }
            is NetworkInfoViewModel.State.Connected -> {
                WanInfoRow(ipAddressString = state.wanIpAddressString)
            }
        }
    }
}