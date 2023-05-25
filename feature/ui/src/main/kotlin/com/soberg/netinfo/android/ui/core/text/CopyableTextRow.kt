package com.soberg.netinfo.android.ui.core.text

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.soberg.netinfo.android.ui.R
import com.soberg.netinfo.android.ui.core.preview.A11yPreview
import com.soberg.netinfo.android.ui.core.preview.ThemedPreview
import com.soberg.netinfo.android.ui.core.theme.Dimens
import com.soberg.netinfo.android.ui.core.theme.TypographyToken

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CopyableTextRow(
    modifier: Modifier = Modifier,
    text: String,
    onCopyTextClicked: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .clickable {
                onCopyTextClicked(text)
            }
            .padding(Dimens.Padding.Base25),
        horizontalArrangement = Arrangement.spacedBy(Dimens.Padding.Base25),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Icon(
            modifier = Modifier
                .size(24.dp),
            painter = painterResource(R.drawable.ic_content_copy),
            contentDescription = null,
        )

        Text.Body(
            modifier = Modifier
                .basicMarquee(),
            text = text,
            token = TypographyToken.Body.Large,
        )
    }
}

@A11yPreview
@Composable
private fun CopyableTextRowPreview() = ThemedPreview {
    CopyableTextRow(
        text = "192.168.0.101",
        onCopyTextClicked = { }
    )
}