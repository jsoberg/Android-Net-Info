package com.soberg.netinfo.android.ui.core.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.soberg.netinfo.android.ui.core.theme.TypographyToken

object Text {

    @Composable
    fun Header(
        text: String,
        modifier: Modifier = Modifier,
        token: TypographyToken.Header = TypographyToken.Header.Medium,
        color: Color = MaterialTheme.colorScheme.onSurface,
    ) {
        Base(
            modifier = modifier,
            text = text,
            token = token,
            color = color,
        )
    }

    @Composable
    fun Body(
        text: String,
        modifier: Modifier = Modifier,
        token: TypographyToken.Body = TypographyToken.Body.Medium,
        color: Color = MaterialTheme.colorScheme.onSurface,
    ) {
        Base(
            modifier = modifier,
            text = text,
            token = token,
            color = color,
        )
    }

    @Composable
    private fun Base(
        text: String,
        modifier: Modifier,
        token: TypographyToken,
        color: Color,
    ) {
        Text(
            modifier = modifier,
            text = text,
            fontSize = token.size,
            lineHeight = token.lineHeight,
            fontWeight = token.weight,
            color = color,
        )
    }

}