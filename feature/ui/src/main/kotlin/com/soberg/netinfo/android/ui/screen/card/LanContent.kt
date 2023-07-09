package com.soberg.netinfo.android.ui.screen.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.soberg.netinfo.android.ui.core.TitledContent
import com.soberg.netinfo.android.ui.core.preview.A11yPreview
import com.soberg.netinfo.android.ui.core.preview.ThemedPreview
import com.soberg.netinfo.android.ui.core.text.CopyableTextRow
import com.soberg.netinfo.android.ui.core.text.IconTextRow
import com.soberg.netinfo.android.ui.core.theme.Dimens
import com.soberg.netinfo.android.ui.screen.state.NetworkInfoViewState
import com.soberg.netinfo.android.ui.screen.state.toDrawableResId
import com.soberg.netinfo.android.ui.screen.state.toTextStringResId
import com.soberg.netinfo.feature.resources.strings.R as StringsR

@Composable
internal fun LanContent(
    modifier: Modifier = Modifier,
    state: NetworkInfoViewState.Ready.Lan,
    onCopyLanIpClicked: () -> Unit,
) {
    when (state) {
        is NetworkInfoViewState.Ready.Lan.Connected -> {
            LanReadyContent(
                modifier = modifier,
                state = state,
                onCopyLanIpClicked = onCopyLanIpClicked,
            )
        }

        is NetworkInfoViewState.Ready.Lan.Unknown -> {
            // TODO: Show error/unknown state
        }
    }
}

@Composable
private fun LanReadyContent(
    modifier: Modifier = Modifier,
    state: NetworkInfoViewState.Ready.Lan.Connected,
    onCopyLanIpClicked: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimens.Padding.Base50),
    ) {

        TitledContent(
            titleResId = StringsR.string.connection_type_content_header,
        ) {
            IconTextRow(
                modifier = Modifier.padding(bottom = Dimens.Padding.Base50),
                text = stringResource(id = state.type.toTextStringResId()),
                color = MaterialTheme.colorScheme.secondary,
                iconDrawableRes = state.type.toDrawableResId(),
            )
        }
        
        TitledContent(
            titleResId = StringsR.string.local_ip_content_header,
        ) {
            CopyableTextRow(
                modifier = Modifier.padding(bottom = Dimens.Padding.Base50),
                text = state.ipAddress,
                color = MaterialTheme.colorScheme.secondary,
                onCopyTextClicked = onCopyLanIpClicked,
            )
        }

    }
}

@A11yPreview
@Composable
private fun LanStateReadyPreview() = ThemedPreview {
    LanContent(
        state = NetworkInfoViewState.Ready.Lan.Connected(
            ipAddress = "192.168.0.1",
            type = NetworkInfoViewState.Ready.Lan.ConnectionType.Wifi,
        ),
        onCopyLanIpClicked = { },
    )
}