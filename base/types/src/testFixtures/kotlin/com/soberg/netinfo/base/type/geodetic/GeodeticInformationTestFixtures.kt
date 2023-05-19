package com.soberg.netinfo.base.type.geodetic

object GeodeticInformationTestFixtures {
    fun get(
        country: Country = Country(
            name = "United States",
            iso = "US",
        ),
        region: Region = Region(
            name = "Kansas",
            code = "KS"
        ),
        zipCode: ZipCode = ZipCode("66546"),
        cityName: String = "Topeka",
        location: Location = Location(
            latitude = 39.0473,
            longitude = -95.6752,
        ),
    ) = GeodeticInformation(
        country = country,
        region = region,
        zipCode = zipCode,
        cityName = cityName,
        location = location,
    )
}