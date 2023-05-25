package com.soberg.netinfo.android.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.soberg.netinfo.android.ui.preview.A11yPreview
import com.soberg.netinfo.android.ui.preview.ThemedPreview
import com.soberg.netinfo.android.ui.text.CopyableTextRow
import com.soberg.netinfo.android.ui.text.Text
import com.soberg.netinfo.android.ui.theme.Dimens

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
        modifier = Modifier
            .fillMaxSize(),
    ) {
        when (state) {
            is NetworkInfoViewModel.State.CannotConnect,
            is NetworkInfoViewModel.State.Loading -> {
                // TODO Add Loading
            }

            is NetworkInfoViewModel.State.Connected -> {
                ConnectedContent(
                    state = state,
                    onCopyWanIpClicked = {
                        // TODO copy ip
                    },
                )
            }
        }
    }
}

@Composable
private fun ConnectedContent(
    state: NetworkInfoViewModel.State.Connected,
    onCopyWanIpClicked: (String) -> Unit,
) {
    WanInfoContent(
        wanIpAddress = state.wanIpAddress,
        onCopyWanIpClicked = onCopyWanIpClicked,
    )
}

@Composable
private fun WanInfoContent(
    wanIpAddress: String,
    onCopyWanIpClicked: (String) -> Unit,
) {
    Column(
        modifier = Modifier.padding(Dimens.Padding.Base100),
        verticalArrangement = Arrangement.spacedBy(Dimens.Padding.Base50),
    ) {
        Text.Header(
            text = stringResource(id = R.string.wan_content_header),
        )

        CopyableTextRow(
            text = wanIpAddress,
            onCopyTextClicked = onCopyWanIpClicked,
        )
    }
}

@A11yPreview
@Composable
private fun NetworkInfoScreenPreview() = ThemedPreview {
    NetworkInfoScreen(
        state = NetworkInfoViewModel.State.Connected(
            wanIpAddress = "109.123.654.321",
            networkTypeDrawableRes = R.drawable.ic_wifi,
        )
    )
}