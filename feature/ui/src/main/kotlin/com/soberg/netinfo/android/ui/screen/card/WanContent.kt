package com.soberg.netinfo.android.ui.screen.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.soberg.netinfo.android.ui.core.TitledContent
import com.soberg.netinfo.android.ui.core.preview.A11yPreview
import com.soberg.netinfo.android.ui.core.preview.ThemedPreview
import com.soberg.netinfo.android.ui.core.text.ClickableIconTextRow
import com.soberg.netinfo.android.ui.core.text.CopyableTextRow
import com.soberg.netinfo.android.ui.core.theme.Dimens
import com.soberg.netinfo.android.ui.core.theme.TypographyToken
import com.soberg.netinfo.android.ui.screen.state.NetworkInfoViewState
import com.soberg.netinfo.feature.resources.drawables.R as DrawablesR
import com.soberg.netinfo.feature.resources.strings.R as StringsR

@Composable
internal fun WanContent(
    modifier: Modifier = Modifier,
    state: NetworkInfoViewState.Ready.Wan,
    onCopyWanIpClicked: () -> Unit,
    onLocationClicked: () -> Unit,
) {
    when (state) {
        is NetworkInfoViewState.Ready.Wan.Connected -> {
            WanReadyContent(
                modifier = modifier,
                state = state,
                onCopyWanIpClicked = onCopyWanIpClicked,
                onLocationClicked = onLocationClicked,
            )
        }

        is NetworkInfoViewState.Ready.Wan.CannotConnect
        -> {
            // TODO: Show can't connect content
        }
    }
}

@Composable
private fun WanReadyContent(
    modifier: Modifier = Modifier,
    state: NetworkInfoViewState.Ready.Wan.Connected,
    onCopyWanIpClicked: () -> Unit,
    onLocationClicked: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimens.Padding.Base50),
    ) {

        TitledContent(
            titleResId = StringsR.string.external_ip_content_header,
        ) {
            CopyableTextRow(
                modifier = Modifier.padding(bottom = Dimens.Padding.Base50),
                text = state.ipAddress,
                token = TypographyToken.Body.Large,
                color = MaterialTheme.colorScheme.secondary,
                onCopyTextClicked = onCopyWanIpClicked,
            )
        }

        state.locationText?.let { locationText ->

            TitledContent(
                titleResId = StringsR.string.ip_geolocation_content_header,
            ) {
                ClickableIconTextRow(
                    modifier = Modifier.padding(bottom = Dimens.Padding.Base50),
                    text = locationText,
                    token = TypographyToken.Body.Large,
                    iconDrawableRes = DrawablesR.drawable.ic_my_location,
                    color = MaterialTheme.colorScheme.secondary,
                    onClicked = onLocationClicked,
                )
            }
        }
    }
}

@A11yPreview
@Composable
private fun WanStateReadyPreview() = ThemedPreview {
    WanContent(
        state = NetworkInfoViewState.Ready.Wan.Connected(
            ipAddress = "109.123.654.321",
            locationText = "New York NY, US"
        ),
        onCopyWanIpClicked = { },
        onLocationClicked = { },
    )
}