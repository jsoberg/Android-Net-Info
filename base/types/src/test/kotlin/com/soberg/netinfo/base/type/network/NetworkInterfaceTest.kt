package com.soberg.netinfo.base.type.network

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
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

    @Test
    fun `return true for canConnectToInternet when Internet property present`() {
        val iface = NetworkInterfaceTestFixtures.get(
            properties = listOf(Properties.Internet)
        )
        assertThat(iface.canConnectToInternet).isTrue()
    }

    @Test
    fun `return false for canConnectToInternet when Internet property not present`() {
        val iface = NetworkInterfaceTestFixtures.get(
            properties = listOf(Properties.VPN)
        )
        assertThat(iface.canConnectToInternet).isFalse()
    }

}