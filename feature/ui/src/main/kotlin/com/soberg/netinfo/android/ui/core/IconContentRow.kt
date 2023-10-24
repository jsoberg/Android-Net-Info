package com.soberg.netinfo.android.ui.core

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.soberg.netinfo.android.ui.core.preview.A11yPreview
import com.soberg.netinfo.android.ui.core.preview.ThemedPreview
import com.soberg.netinfo.android.ui.core.text.Text
import com.soberg.netinfo.android.ui.core.theme.Dimens
import com.soberg.netinfo.android.ui.core.theme.TypographyToken
import com.soberg.netinfo.feature.resources.drawables.R as DrawablesR

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IconContentRow(
    modifier: Modifier = Modifier,
    titleText: String,
    contentText: String,
    @DrawableRes
    iconDrawableRes: Int,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Icon(
            modifier = Modifier
                .size(Dimens.Sizes.RowIcon)
                .padding(end = Dimens.Padding.Base75),
            painter = painterResource(iconDrawableRes),
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = null,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.Padding.Base25),
        ) {
            Text.Title(
                text = titleText,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Text.Body(
                modifier = Modifier.basicMarquee(),
                text = contentText,
                token = TypographyToken.Body.Medium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@A11yPreview
@Composable
private fun IconContentRowPreview() = ThemedPreview {
    IconContentRow(
        modifier = Modifier.padding(Dimens.Padding.Base75),
        titleText = "Title text",
        contentText = "Description text",
        iconDrawableRes = DrawablesR.drawable.ic_content_copy,
    )
}