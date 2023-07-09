package com.soberg.netinfo.android.ui.core

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.soberg.netinfo.android.ui.core.text.Text

@Composable
fun ColumnScope.TitledContent(
    @StringRes
    titleResId: Int,
    content: @Composable () -> Unit,
) {
    Text.Title(
        text = stringResource(id = titleResId),
        color = MaterialTheme.colorScheme.primary,
    )

    content()
}