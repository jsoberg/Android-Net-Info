package com.soberg.netinfo.android.ui.core.pulltorefresh

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import kotlinx.coroutines.delay
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshBox(
    onRefreshStarted: () -> Unit,
    modifier: Modifier = Modifier,
    refreshDelay: Duration = 1.5.seconds,
    content: @Composable () -> Unit,
) {
    val pullRefreshState = rememberPullToRefreshState()
    if (pullRefreshState.isRefreshing) {
        LaunchedEffect(true) {
            onRefreshStarted()
            delay(refreshDelay)
            pullRefreshState.endRefresh()
        }
    }

    val scaleFraction = if (pullRefreshState.isRefreshing) {
        1f
    } else {
        LinearOutSlowInEasing.transform(pullRefreshState.progress)
            .coerceIn(0f, 1f)
    }

    Box(
        modifier.nestedScroll(pullRefreshState.nestedScrollConnection),
    ) {
        content()

        PullToRefreshContainer(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .graphicsLayer(scaleX = scaleFraction, scaleY = scaleFraction),
            state = pullRefreshState,
        )
    }
}