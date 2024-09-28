package com.soberg.netinfo.android.ui.core.pulltorefresh

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshContainer(
    onRefreshStarted: () -> Unit,
    modifier: Modifier = Modifier,
    refreshDelay: Duration = 1.5.seconds,
    content: @Composable () -> Unit,
) {
    val pullRefreshState = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val onRefresh: () -> Unit = {
        isRefreshing = true
        onRefreshStarted()
        coroutineScope.launch {
            delay(refreshDelay)
            isRefreshing = false
        }
    }

    PullToRefreshBox(
        modifier = modifier,
        state = pullRefreshState,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
    ) {
        content()
    }
}