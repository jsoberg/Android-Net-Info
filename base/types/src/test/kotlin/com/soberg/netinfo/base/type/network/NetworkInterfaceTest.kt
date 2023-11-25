package com.soberg.netinfo.base.type.network

import com.google.common.truth.Truth.assertThat
import com.soberg.netinfo.base.type.network.NetworkInterface.Properties
import org.junit.jupiter.api.Test

internal class NetworkInterfaceTest {

    @Test
    fun `return true for isConnectedToVpn when VPN property present`() {
        val iface = NetworkInterfaceTestFixtures.get(
            properties = listOf(Properties.VPN)
        )
        assertThat(iface.isConnectedToVpn).isTrue()
    }

    @Test
    fun `return false for isConnectedToVpn when VPN property not present`() {
        val iface = NetworkInterfaceTestFixtures.get(
            properties = listOf(Properties.Internet)
        )
        assertThat(iface.isConnectedToVpn).isFalse()
    }

}