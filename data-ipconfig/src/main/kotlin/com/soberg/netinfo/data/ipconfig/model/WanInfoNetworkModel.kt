package com.soberg.netinfo.data.ipconfig.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class WanInfoNetworkModel(
    // WAN information
    @SerialName("ip")
    val ip: String? = null,

    // Location information
    @SerialName("country")
    val country: String? = null,
    @SerialName("country_iso")
    val countryIso: String? = null,
    @SerialName("region_name")
    val regionName: String? = null,
    @SerialName("region_code")
    val regionCode: String? = null,
    @SerialName("zip_code")
    val zipCode: String? = null,
    @SerialName("city")
    val city: String? = null,

    @SerialName("latitude")
    val latitude: Double? = null,
    @SerialName("longitude")
    val longitude: Double? = null,
)