package com.soberg.netinfo.android.ui.core.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.soberg.netinfo.android.ui.core.theme.TypographyToken

object Text {

    @Composable
    fun Header(
        text: String,
        modifier: Modifier = Modifier,
        token: TypographyToken.Header = TypographyToken.Header.Medium,
    ) {
        Base(
            modifier = modifier,
            text = text,
            token = token,
        )
    }

    @Composable
    fun Body(
        text: String,
        modifier: Modifier = Modifier,
        token: TypographyToken.Body = TypographyToken.Body.Medium,
    ) {
        Base(
            modifier = modifier,
            text = text,
            token = token,
        )
    }

    @Composable
    private fun Base(
        text: String,
        modifier: Modifier,
        token: TypographyToken,
    ) {
        Text(
            modifier = modifier,
            text = text,
            fontSize = token.size,
            lineHeight = token.lineHeight,
            fontWeight = token.weight,
        )
    }

}