package com.soberg.netinfo.android.ui.core

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.soberg.netinfo.android.ui.core.text.Text
import com.soberg.netinfo.android.ui.core.theme.Dimens

@Composable
fun TitledContent(
    @StringRes
    titleResId: Int,
    content: @Composable () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimens.Padding.Base50),
    ) {
        Text.Title(
            text = stringResource(id = titleResId),
            color = MaterialTheme.colorScheme.primary,
        )

        content()
    }
}