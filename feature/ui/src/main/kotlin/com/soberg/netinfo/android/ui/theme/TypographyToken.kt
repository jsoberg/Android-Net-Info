package com.soberg.netinfo.android.ui.theme

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/** Following [https://m3.material.io/styles/typography/type-scale-tokens]. */
sealed interface TypographyToken {

    val size: TextUnit
    val lineHeight: TextUnit
    val weight: FontWeight

    enum class Header(
        override val size: TextUnit,
        override val lineHeight: TextUnit,
    ) : TypographyToken {
        Large(
            size = 32.sp,
            lineHeight = 40.sp
        ),
        Medium(
            size = 28.sp,
            lineHeight = 36.sp
        ),
        Small(
            size = 24.sp,
            lineHeight = 32.sp
        );

        override val weight: FontWeight = FontWeight.W400
    }

    enum class Body(
        override val size: TextUnit,
        override val lineHeight: TextUnit,
    ) : TypographyToken {
        Large(
            size = 16.sp,
            lineHeight = 24.sp
        ),
        Medium(
            size = 14.sp,
            lineHeight = 20.sp
        ),
        Small(
            size = 12.sp,
            lineHeight = 16.sp
        );

        override val weight: FontWeight = FontWeight.W400
    }
}