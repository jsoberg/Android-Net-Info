package com.soberg.netinfo.data.ipconfig.model

import com.google.common.truth.Truth.assertThat
import com.soberg.netinfo.base.type.geodetic.Country
import com.soberg.netinfo.base.type.geodetic.GeodeticInformation
import com.soberg.netinfo.base.type.geodetic.Location
import com.soberg.netinfo.base.type.geodetic.Region
import com.soberg.netinfo.base.type.geodetic.ZipCode
import com.soberg.netinfo.base.type.network.IpAddress
import com.soberg.netinfo.domain.wan.model.WanInfo
import org.junit.jupiter.api.Test

internal class WanInfoNetworkModelConversionsTest {

    @Test
    fun `convert to expected values successfully when all values present`() {
        val result = WanInfoNetworkModelTestFixtures.get(
            ip = "10.9.8.7",
            country = "Netherlands",
            countryIso = "NL",
            regionName = "North Holland",
            regionCode = "NH",
            zipCode = "1012",
            city = "Amsterdam",
            latitude = 52.3759,
            longitude = 4.8975,
        ).toDomain()
        assertThat(result.isSuccess).isTrue()

        val expected = WanInfo(
            ip = IpAddress("10.9.8.7"),
            ispGeoInfo = GeodeticInformation(
                country = Country(
                    name = "Netherlands",
                    iso = "NL",
                ),
                region = Region(
                    name = "North Holland",
                    code = "NH",
                ),
                zipCode = ZipCode("1012"),
                cityName = "Amsterdam",
                Location(
                    latitude = 52.3759,
                    longitude = 4.8975,
                )
            )
        )
        assertThat(result.getOrThrow()).isEqualTo(expected)
    }

    @Test
    fun `convert to expected values successfully when only IP present`() {
        val result = WanInfoNetworkModelTestFixtures.get(
            ip = "10.9.8.7",
            country = null,
            countryIso = null,
            regionName = null,
            regionCode = null,
            zipCode = null,
            city = null,
            latitude = null,
            longitude = null,
        ).toDomain()
        assertThat(result.isSuccess).isTrue()

        val expected = WanInfo(
            ip = IpAddress("10.9.8.7"),
            ispGeoInfo = null,
        )
        assertThat(result.getOrThrow()).isEqualTo(expected)
    }

    @Test
    fun `not include geodetic info when one geodetic data point isn't included`() {
        val result = WanInfoNetworkModelTestFixtures.get(
            ip = "10.9.8.7",
            country = null,
        ).toDomain()
        assertThat(result.isSuccess).isTrue()

        val expected = WanInfo(
            ip = IpAddress("10.9.8.7"),
            ispGeoInfo = null,
        )
        assertThat(result.getOrThrow()).isEqualTo(expected)
    }

    @Test
    fun `fail to convert if IP address not present`() {
        val result = WanInfoNetworkModelTestFixtures.get(
            ip = null,
        ).toDomain()
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull())
            .hasMessageThat()
            .isEqualTo("IP address must be present")
    }
}