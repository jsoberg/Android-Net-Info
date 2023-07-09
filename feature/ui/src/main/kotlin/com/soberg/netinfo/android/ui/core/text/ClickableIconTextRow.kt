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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.soberg.netinfo.android.ui.core.preview.A11yPreview
import com.soberg.netinfo.android.ui.core.preview.ThemedPreview
import com.soberg.netinfo.android.ui.core.theme.Dimens
import com.soberg.netinfo.android.ui.core.theme.TypographyToken
import com.soberg.netinfo.feature.resources.drawables.R as DrawablesR

@Composable
fun CopyableTextRow(
    modifier: Modifier = Modifier,
    text: String,
    token: TypographyToken.Body,
    color: Color,
    onCopyTextClicked: () -> Unit,
) {
    ClickableIconTextRow(
        modifier = modifier,
        text = text,
        token = token,
        iconDrawableRes = DrawablesR.drawable.ic_content_copy,
        color = color,
        onClicked = onCopyTextClicked,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClickableIconTextRow(
    modifier: Modifier = Modifier,
    text: String,
    token: TypographyToken.Body,
    color: Color,
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
            tint = color,
            contentDescription = null,
        )

        Text.Body(
            modifier = Modifier
                .basicMarquee(),
            text = text,
            token = token,
            color = color,
        )
    }
}


@A11yPreview
@Composable
private fun CopyableTextRowPreview() = ThemedPreview {
    CopyableTextRow(
        text = "192.168.0.101",
        token = TypographyToken.Body.Large,
        color = MaterialTheme.colorScheme.primary,
        onCopyTextClicked = { },
    )
}