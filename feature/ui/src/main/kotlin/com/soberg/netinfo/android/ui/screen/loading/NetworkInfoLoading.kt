package com.soberg.netinfo.android.ui.screen.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.soberg.netinfo.android.ui.core.preview.A11yPreview
import com.soberg.netinfo.android.ui.core.preview.ThemedPreview
import com.soberg.netinfo.feature.resources.lottie.R as LottieR

@Composable
fun NetworkInfoLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(LottieR.raw.lottie_loading_animation))
        val progress by animateLottieCompositionAsState(
            composition = composition,
            speed = 1.25f,
            iterations = LottieConstants.IterateForever,
        )

        val dynamicProperties = rememberLottieDynamicProperties(
            rememberLottieDynamicProperty(
                property = LottieProperty.COLOR_FILTER,
                value = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    MaterialTheme.colorScheme.primary.toArgb(),
                    BlendModeCompat.SRC_ATOP
                ),
                keyPath = arrayOf("**")
            )
        )
        LottieAnimation(
            modifier = Modifier
                .align(Alignment.Center)
                .size(256.dp),
            composition = composition,
            progress = { progress },
            dynamicProperties = dynamicProperties,
        )
    }
}

@A11yPreview
@Composable
private fun NetworkInfoLoadingPreview() = ThemedPreview {
    NetworkInfoLoading(
        modifier = Modifier.fillMaxSize(),
    )
}