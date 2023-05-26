package com.soberg.netinfo.android.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.soberg.netinfo.android.ui.R
import com.soberg.netinfo.android.ui.core.preview.A11yPreview
import com.soberg.netinfo.android.ui.core.preview.ThemedPreview
import com.soberg.netinfo.android.ui.core.text.ClickableIconTextRow
import com.soberg.netinfo.android.ui.core.text.CopyableTextRow
import com.soberg.netinfo.android.ui.core.text.Text
import com.soberg.netinfo.android.ui.core.theme.Dimens
import com.soberg.netinfo.android.ui.core.theme.TypographyToken

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
                    state = state.wan,
                    onCopyWanIpClicked = {
                        // TODO copy ip
                    },
                    onLocationClicked = {
                        // TODO open maps
                    }
                )
            }
        }
    }
}

@Composable
private fun ConnectedContent(
    state: NetworkInfoViewModel.State.Connected.Wan,
    onCopyWanIpClicked: () -> Unit,
    onLocationClicked: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(Dimens.Padding.Base100),
        verticalArrangement = Arrangement.spacedBy(Dimens.Padding.Base50),
    ) {
        Text.Header(
            text = stringResource(id = R.string.wan_content_header),
            color = MaterialTheme.colorScheme.primary,
        )

        CopyableTextRow(
            text = state.ipAddress,
            token = TypographyToken.Body.Large,
            color = MaterialTheme.colorScheme.secondary,
            onCopyTextClicked = onCopyWanIpClicked,
        )

        state.locationText?.let { locationText ->
            ClickableIconTextRow(
                text = locationText,
                token = TypographyToken.Body.Large,
                iconDrawableRes = R.drawable.ic_my_location,
                color = MaterialTheme.colorScheme.secondary,
                onClicked = onLocationClicked,
            )
        }

    }
}

@A11yPreview
@Composable
private fun NetworkInfoScreenPreview() = ThemedPreview {
    NetworkInfoScreen(
        state = NetworkInfoViewModel.State.Connected(
            wan = NetworkInfoViewModel.State.Connected.Wan(
                ipAddress = "109.123.654.321",
                locationText = "New York NY, US"
            ),
            networkTypeDrawableRes = R.drawable.ic_wifi,
        )
    )
}