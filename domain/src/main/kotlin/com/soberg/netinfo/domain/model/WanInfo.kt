package com.soberg.netinfo.domain.model

import com.soberg.netinfo.base.type.geodetic.GeodeticInformation
import com.soberg.netinfo.base.type.network.Ipv4Address

data class WanInfo(
    /** Required: WAN IPV4 Address for this device. */
    val ip: Ipv4Address,
    /** Optional: [GeodeticInformation] pertaining to the ISP supplying this WAN information. */
    val ispGeoInfo: GeodeticInformation?,
)