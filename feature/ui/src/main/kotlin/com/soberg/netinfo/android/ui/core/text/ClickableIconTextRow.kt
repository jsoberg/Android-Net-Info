package com.soberg.netinfo.android.ui.core.text

import androidx.annotation.DrawableRes
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

@Composable
fun CopyableTextRow(
    modifier: Modifier = Modifier,
    text: String,
    token: TypographyToken.Body,
    onCopyTextClicked: () -> Unit,
) {
    ClickableIconTextRow(
        modifier = modifier,
        text = text,
        token = token,
        iconDrawableRes = R.drawable.ic_content_copy,
        onClicked = onCopyTextClicked,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClickableIconTextRow(
    modifier: Modifier = Modifier,
    text: String,
    token: TypographyToken.Body,
    @DrawableRes
    iconDrawableRes: Int,
    onClicked: () -> Unit,
) {
    Row(
        modifier = modifier
            .clickable {
                onClicked()
            }
            .padding(Dimens.Padding.Base25),
        horizontalArrangement = Arrangement.spacedBy(Dimens.Padding.Base50),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Icon(
            modifier = Modifier
                .size(24.dp),
            painter = painterResource(iconDrawableRes),
            contentDescription = null,
        )

        Text.Body(
            modifier = Modifier
                .basicMarquee(),
            text = text,
            token = token,
        )
    }
}


@A11yPreview
@Composable
private fun CopyableTextRowPreview() = ThemedPreview {
    CopyableTextRow(
        text = "192.168.0.101",
        token = TypographyToken.Body.Large,
        onCopyTextClicked = { },
    )
}