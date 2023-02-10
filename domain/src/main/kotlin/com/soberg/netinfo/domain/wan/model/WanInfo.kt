package com.soberg.netinfo.domain.wan.model

import com.soberg.netinfo.base.type.geodetic.GeodeticInformation
import com.soberg.netinfo.base.type.network.IpAddress

data class WanInfo(
    /** Required: WAN IPV4 Address for this device. */
    val ip: IpAddress,
    /** Optional: [GeodeticInformation] pertaining to the ISP supplying this WAN information. */
    val ispGeoInfo: GeodeticInformation?,
)