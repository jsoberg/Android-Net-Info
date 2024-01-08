package com.soberg.netinfo.android.ui.screen.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.soberg.netinfo.android.ui.core.preview.A11yPreview
import com.soberg.netinfo.android.ui.core.preview.ThemedPreview

@Composable
fun NetworkInfoLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.Center),
        ) {

            CircularProgressIndicator(
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }

    }
}

@A11yPreview
@Composable
private fun NetworkInfoLoadingPreview() = ThemedPreview {
    NetworkInfoLoading(
        modifier = Modifier.fillMaxSize(),
    )
}