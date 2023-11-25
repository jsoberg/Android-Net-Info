package com.soberg.netinfo.android.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.soberg.netinfo.android.ui.core.preview.A11yPreview
import com.soberg.netinfo.android.ui.core.preview.ThemedPreview
import com.soberg.netinfo.android.ui.core.text.Text
import com.soberg.netinfo.android.ui.core.theme.Dimens

@Composable
fun AppToolbar(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                start = Dimens.Padding.Base100,
                top = Dimens.Padding.Base50,
                bottom = Dimens.Padding.Base50,
            )
            .wrapContentHeight()
            .fillMaxWidth(),
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(Dimens.Sizes.ToolbarIcon)
                .padding(end = Dimens.Padding.Base75),
            painter = painterResource(R.drawable.app_icon),
            tint = Color.Unspecified,
            contentDescription = null,
        )

        Text.Header(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            text = stringResource(id = R.string.app_name)
        )
    }
}

@A11yPreview
@Composable
fun AppToolbarPreview() = ThemedPreview {
    AppToolbar()
}