package com.soberg.netinfo.android.data.netconnectivity

import android.content.Context
import android.net.ConnectivityManager
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.soberg.netinfo.domain.lan.NetworkConnectionRepository.State
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)
internal class AndroidNetworkConnectionRepositoryTest {

    private val testDispatcher = StandardTestDispatcher()
    private val coroutineScope = TestScope(testDispatcher)

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val connectionRepository = AndroidNetworkConnectionRepository(
        appCoroutineScope = coroutineScope,
        connectivityManager = connectivityManager,
    )

    @Test
    fun `emit not connected when there is no active network`() = runTest(testDispatcher) {
        shadowOf(connectivityManager).setDefaultNetworkActive(false)
        connectionRepository.activeConnectionStateFlow.test {
            assertThat(awaitItem()).isEqualTo(State.NotConnected)
        }
    }
}