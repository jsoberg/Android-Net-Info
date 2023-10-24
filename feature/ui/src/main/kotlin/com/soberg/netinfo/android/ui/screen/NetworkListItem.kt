package com.soberg.netinfo.android.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.soberg.netinfo.android.ui.core.IconContentRow
import com.soberg.netinfo.android.ui.core.theme.Dimens

@Composable
internal fun NetworkListItem(
    modifier: Modifier = Modifier,
    titleText: String,
    contentText: String,
    @DrawableRes
    iconDrawableRes: Int,
) {
    IconContentRow(
        modifier = modifier
            .padding(
                horizontal = Dimens.Padding.Base100,
                vertical = Dimens.Padding.Base50,
            ),
        titleText = titleText,
        contentText = contentText,
        iconDrawableRes = iconDrawableRes,
    )
}