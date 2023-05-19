package com.soberg.netinfo.base.type.geodetic

data class GeodeticInformation(
    val country: Country,
    val region: Region,
    val zipCode: ZipCode,
    val cityName: String,
    val location: Location,
)
