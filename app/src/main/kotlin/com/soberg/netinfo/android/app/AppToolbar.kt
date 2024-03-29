package com.soberg.netinfo.android.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
                start = Dimens.Padding.Base75,
                top = Dimens.Padding.Base50,
                bottom = Dimens.Padding.Base50,
            )
            .wrapContentHeight()
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .background(Color.White, shape = CircleShape)
                .size(Sizes.IconBackground),
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(Sizes.Icon),
                painter = painterResource(R.drawable.app_icon),
                tint = Color.Unspecified,
                contentDescription = null,
            )
        }

        Text.Header(
            modifier = Modifier
                .padding(start = Dimens.Padding.Base75)
                .align(Alignment.CenterVertically),
            text = stringResource(id = R.string.app_name)
        )
    }
}

private object Sizes {
    val Icon = 36.dp
    val IconBackground = 40.dp
}

@A11yPreview
@Composable
fun AppToolbarPreview() = ThemedPreview {
    AppToolbar()
}