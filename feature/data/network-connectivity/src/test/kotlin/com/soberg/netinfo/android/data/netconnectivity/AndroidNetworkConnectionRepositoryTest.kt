package com.soberg.netinfo.android.data.netconnectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.LinkProperties
import android.net.NetworkCapabilities
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.soberg.netinfo.android.data.netconnectivity.local.FindLocalIpAddressUseCase
import com.soberg.netinfo.base.type.network.NetworkInterface.Properties.Internet
import com.soberg.netinfo.base.type.network.NetworkInterface.Properties.VPN
import com.soberg.netinfo.base.type.network.NetworkInterface.Type.Cellular
import com.soberg.netinfo.base.type.network.NetworkInterface.Type.Wifi
import com.soberg.netinfo.domain.lan.NetworkConnectionRepository.State
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowNetworkCapabilities

@RunWith(RobolectricTestRunner::class)
internal class AndroidNetworkConnectionRepositoryTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private val coroutineScope = TestScope(testDispatcher)

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val findLocalIpAddress: FindLocalIpAddressUseCase = mockk {
        every { this@mockk.invoke(any()) } returns null
    }

    private val connectionRepository = AndroidNetworkConnectionRepository(
        appCoroutineScope = coroutineScope,
        connectivityManager = connectivityManager,
        findLocalIpAddress = findLocalIpAddress,
    )

    @Test
    fun `register a network callback when subscribed`() = runTest {
        assertThat(callbackCount).isEqualTo(0)
        connectionRepository.activeConnectionStateFlow.test {
            awaitItem()
            assertThat(callbackCount).isEqualTo(1)
        }
    }

    @Test
    fun `unregister a network callback when unsubscribed`() = runTest {
        assertThat(callbackCount).isEqualTo(0)
        connectionRepository.activeConnectionStateFlow.test {
            awaitItem()
            assertThat(callbackCount).isEqualTo(1)
        }
        assertThat(callbackCount).isEqualTo(0)
    }

    @Test
    fun `register only one network callback for multiple subscribers`() = runTest {
        assertThat(callbackCount).isEqualTo(0)
        // First subscriber
        connectionRepository.activeConnectionStateFlow.test {
            awaitItem()
            assertThat(callbackCount).isEqualTo(1)

            // Second subscriber
            connectionRepository.activeConnectionStateFlow.test {
                awaitItem()
                assertThat(callbackCount).isEqualTo(1)
            }
        }
    }

    @Test
    fun `emit not connected when there is no active network`() = runTest {
        shadowOf(connectivityManager).setDefaultNetworkActive(false)
        connectionRepository.activeConnectionStateFlow.test {
            assertThat(awaitItem()).isEqualTo(State.NoActiveConnection)
        }
    }

    @Test
    fun `emit network changes from active network for onAvailable`() = runTest {
        shadowOf(connectivityManager).setDefaultNetworkActive(false)
        connectionRepository.activeConnectionStateFlow.test {
            assertThat(awaitItem()).isEqualTo(State.NoActiveConnection)

            shadowOf(connectivityManager).setDefaultNetworkActive(true)
            mockActiveNetwork(capabilities = listOf(NetworkCapabilities.NET_CAPABILITY_INTERNET))
            callback.onAvailable(mockk())
            val second = awaitItem() as State.Connected
            assertThat(second.netInterface.properties).containsExactly(Internet)
        }
    }

    @Test
    fun `emit network changes from active network for onCapabilitiesChanged`() = runTest {
        mockActiveNetwork(capabilities = listOf(NetworkCapabilities.NET_CAPABILITY_INTERNET))
        connectionRepository.activeConnectionStateFlow.test {
            val first = awaitItem() as State.Connected
            assertThat(first.netInterface.properties).containsExactly(Internet)

            mockActiveNetwork(capabilities = emptyList())
            callback.onCapabilitiesChanged(mockk(), mockk())
            val second = awaitItem() as State.Connected
            assertThat(second.netInterface.properties).isEmpty()
        }
    }

    @Test
    fun `emit network changes from active network for onLinkPropertiesChanged`() = runTest {
        mockActiveNetwork(capabilities = listOf(NetworkCapabilities.NET_CAPABILITY_INTERNET))
        connectionRepository.activeConnectionStateFlow.test {
            val first = awaitItem() as State.Connected
            assertThat(first.netInterface.properties).containsExactly(Internet)

            mockActiveNetwork(
                transportTypes = listOf(
                    NetworkCapabilities.TRANSPORT_WIFI,
                    NetworkCapabilities.TRANSPORT_VPN
                ),
                capabilities = listOf(NetworkCapabilities.NET_CAPABILITY_INTERNET),
            )
            callback.onLinkPropertiesChanged(mockk(), mockk())
            val second = awaitItem() as State.Connected
            assertThat(second.netInterface.properties).containsExactly(Internet, VPN)
        }
    }

    @Test
    fun `emit network changes from active network for onLost`() = runTest {
        mockActiveNetwork(transportTypes = listOf(NetworkCapabilities.TRANSPORT_WIFI))
        connectionRepository.activeConnectionStateFlow.test {
            val first = awaitItem() as State.Connected
            assertThat(first.netInterface.type).isEqualTo(Wifi)

            mockActiveNetwork(transportTypes = listOf(NetworkCapabilities.TRANSPORT_CELLULAR))
            callback.onLost(mockk())
            val second = awaitItem() as State.Connected
            assertThat(second.netInterface.type).isEqualTo(Cellular)
        }
    }

    @Test
    fun `emit network changes from active network for onUnavailable`() = runTest {
        mockActiveNetwork(interfaceName = "wlan0")
        connectionRepository.activeConnectionStateFlow.test {
            val first = awaitItem() as State.Connected
            assertThat(first.netInterface.name).isEqualTo("wlan0")

            mockActiveNetwork(interfaceName = "eth0")
            callback.onUnavailable()
            val second = awaitItem() as State.Connected
            assertThat(second.netInterface.name).isEqualTo("eth0")
        }
    }

    private val callbackCount: Int
        get() = shadowOf(connectivityManager).networkCallbacks.size

    private val callback: NetworkCallback
        get() = shadowOf(connectivityManager).networkCallbacks.first()

    private fun mockActiveNetwork(
        interfaceName: String? = "wlan0",
        transportTypes: List<Int> = listOf(NetworkCapabilities.TRANSPORT_WIFI),
        capabilities: List<Int> = listOf(NetworkCapabilities.NET_CAPABILITY_INTERNET),
    ) {
        val shadowCapabilities = ShadowNetworkCapabilities.newInstance()
        transportTypes.forEach { transport ->
            shadowOf(shadowCapabilities).addTransportType(transport)
        }
        capabilities.forEach { capability ->
            shadowOf(shadowCapabilities).addCapability(capability)
        }

        shadowOf(connectivityManager).setLinkProperties(
            connectivityManager.activeNetwork,
            LinkProperties().apply { setInterfaceName(interfaceName) }
        )
        shadowOf(connectivityManager).setNetworkCapabilities(
            connectivityManager.activeNetwork, shadowCapabilities
        )
    }
}