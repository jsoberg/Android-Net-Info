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
import com.soberg.netinfo.android.ui.core.text.CopyableTextRow
import com.soberg.netinfo.android.ui.core.text.Text
import com.soberg.netinfo.android.ui.core.theme.Dimens
import com.soberg.netinfo.android.ui.core.theme.TypographyToken
import com.soberg.netinfo.android.ui.screen.NetworkInfoViewModel

@Composable
internal fun LanCard(
    state: NetworkInfoViewModel.LanState,
    onCopyLanIpClicked: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(Dimens.Padding.Base50)
            .wrapContentHeight()
            .fillMaxWidth(),
    ) {
        when (state) {
            is NetworkInfoViewModel.LanState.Ready -> {
                LanReadyContent(
                    state = state,
                    onCopyLanIpClicked = onCopyLanIpClicked,
                )
            }

            is NetworkInfoViewModel.LanState.Unknown -> {
                // TODO: Show error/unknown state
            }
        }
    }
}

@Composable
private fun LanReadyContent(
    state: NetworkInfoViewModel.LanState.Ready,
    onCopyLanIpClicked: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(Dimens.Padding.Base100),
        verticalArrangement = Arrangement.spacedBy(Dimens.Padding.Base50),
    ) {
        Text.Header(
            text = stringResource(id = R.string.lan_content_header),
            color = MaterialTheme.colorScheme.primary,
        )

        CopyableTextRow(
            text = state.ipAddress,
            token = TypographyToken.Body.Large,
            color = MaterialTheme.colorScheme.secondary,
            onCopyTextClicked = onCopyLanIpClicked,
        )
    }
}

@A11yPreview
@Composable
private fun LanStateReadyPreview() = ThemedPreview {
    LanCard(
        state = NetworkInfoViewModel.LanState.Ready(
            ipAddress = "192.168.0.1",
        ),
        onCopyLanIpClicked = { },
    )
}