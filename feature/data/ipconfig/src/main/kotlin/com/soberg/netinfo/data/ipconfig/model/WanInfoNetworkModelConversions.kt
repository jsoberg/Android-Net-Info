@file:JvmName("WanInfoNetworkModelConversions")

package com.soberg.netinfo.data.ipconfig.model

import com.soberg.netinfo.base.type.geodetic.*
import com.soberg.netinfo.base.type.network.IpAddress
import com.soberg.netinfo.domain.wan.model.WanInfo

internal fun WanInfoNetworkModel.toDomain(): Result<WanInfo> = runCatching {
    WanInfo(
        ip = ip?.let(::IpAddress) ?: error("IP address must be present"),
        ispGeoInfo = toIspGeoInfo().getOrNull(),
    )
}

private fun WanInfoNetworkModel.toIspGeoInfo(): Result<GeodeticInformation> = runCatching {
    GeodeticInformation(
        country = Country(
            name = country ?: error("Country name must be present"),
            iso = countryIso ?: error("Country ISO must be present"),
        ),
        region = Region(
            name = regionName ?: error("Region name must be present"),
            code = regionCode ?: error("Region code must be present"),
        ),
        zipCode = zipCode?.let(::ZipCode) ?: error("Zip code must be present"),
        cityName = city ?: error("City name must be present"),
        location = Location(
            latitude = latitude ?: error("Latitude must be present"),
            longitude = longitude ?: error("Longitude must be present"),
        )
    )
}