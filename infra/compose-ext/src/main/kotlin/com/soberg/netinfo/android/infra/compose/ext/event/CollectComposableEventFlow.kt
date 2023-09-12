package com.soberg.netinfo.android.infra.compose.ext.event

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.Flow

/** Safely collects this flow for a composable context, repeated on the [state] lifecycle event and
 * delivering all emitted events to [handleEvent]. */
@Composable
fun <Event> Flow<Event>.CollectComposableEventFlow(
    state: Lifecycle.State = Lifecycle.State.STARTED,
    handleEvent: (Event) -> Unit,
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(Unit) {
        flowWithLifecycle(
            lifecycle = lifecycle,
            minActiveState = state,
        ).collect(handleEvent)
    }
}