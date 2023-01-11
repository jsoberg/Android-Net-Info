package com.soberg.netinfo.domain.model

import com.soberg.netinfo.base.type.geodetic.GeodeticInformation
import com.soberg.netinfo.base.type.network.Ipv4Address

data class WanInfo(
    val ip: Ipv4Address,
    val ispGeoInfo: GeodeticInformation,
)