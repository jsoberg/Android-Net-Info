package com.soberg.netinfo.data.ipconfig

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class WanInfoNetworkModel(
    // WAN information
    @SerialName("ip")
    val ip: String?,
    @SerialName("hostname")
    val hostname: String?,

    // Location information
    @SerialName("country")
    val country: String?,
    @SerialName("country_iso")
    val countryIso: String?,
    @SerialName("region_name")
    val regionName: String?,
    @SerialName("region_code")
    val regionCode: String?,
    @SerialName("zip_code")
    val zipCode: String?,
    @SerialName("city")
    val city: String?,

    @SerialName("latitude")
    val latitude: Double?,
    @SerialName("longitude")
    val longitude: Double?,
)