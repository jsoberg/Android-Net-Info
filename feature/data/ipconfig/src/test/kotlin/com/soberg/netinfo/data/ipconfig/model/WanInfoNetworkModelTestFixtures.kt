package com.soberg.netinfo.data.ipconfig.model

internal object WanInfoNetworkModelTestFixtures {

    fun get(
        ip: String? = "192.168.0.1",
        country: String? = "United States",
        countryIso: String? = "US",
        regionName: String? = "California",
        regionCode: String? = "CA",
        zipCode: String? = "94016",
        city: String? = "San Francisco",
        latitude: Double? = 37.7749,
        longitude: Double? = -122.4194,
    ) = WanInfoNetworkModel(
        ip = ip,
        country = country,
        countryIso = countryIso,
        regionName = regionName,
        regionCode = regionCode,
        zipCode = zipCode,
        city = city,
        latitude = latitude,
        longitude = longitude,
    )
}