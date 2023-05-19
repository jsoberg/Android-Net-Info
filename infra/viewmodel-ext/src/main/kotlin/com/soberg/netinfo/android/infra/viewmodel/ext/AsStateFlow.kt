package com.soberg.netinfo.android.infra.viewmodel.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

const val SharingSubscriptionMillis = 5000L

/** @return A [StateFlow] shared in this [ViewModel]'s [viewModelScope] spawning from the flow created by [flowCreator]. */
inline fun <T> ViewModel.asStateFlow(initialValue: T, flowCreator: () -> Flow<T>): StateFlow<T> =
    flowCreator().stateIn(viewModelScope, WhileSubscribed(SharingSubscriptionMillis), initialValue)