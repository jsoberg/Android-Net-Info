package com.soberg.netinfo.android.ui.screen.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
import com.soberg.netinfo.android.ui.screen.NetworkInfoViewModel

@Composable
internal fun WanCard(
    state: NetworkInfoViewModel.WanState,
    onCopyWanIpClicked: () -> Unit,
    onLocationClicked: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(Dimens.Padding.Base50)
            .wrapContentHeight()
            .fillMaxWidth(),
    ) {
        when (state) {
            is NetworkInfoViewModel.WanState.Ready -> {
                WanReadyContent(
                    state = state,
                    onCopyWanIpClicked = onCopyWanIpClicked,
                    onLocationClicked = onLocationClicked,
                )
            }

            is NetworkInfoViewModel.WanState.CannotConnect
            -> {
                // TODO: Show can't connect content
            }
        }
    }
}

@Composable
private fun WanReadyContent(
    state: NetworkInfoViewModel.WanState.Ready,
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
private fun WanStateReadyPreview() = ThemedPreview {
    WanCard(
        state = NetworkInfoViewModel.WanState.Ready(
            ipAddress = "109.123.654.321",
            locationText = "New York NY, US"
        ),
        onCopyWanIpClicked = { },
        onLocationClicked = { },
    )
}