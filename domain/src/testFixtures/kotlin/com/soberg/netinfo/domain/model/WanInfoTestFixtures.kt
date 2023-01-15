package com.soberg.netinfo.domain.model

import com.soberg.netinfo.base.type.geodetic.GeodeticInformation
import com.soberg.netinfo.base.type.geodetic.GeodeticInformationTestFixtures
import com.soberg.netinfo.base.type.network.IpAddress

object WanInfoTestFixtures {

    fun get(
        ip: IpAddress = IpAddress("192.168.1.1"),
        ispGeoInfo: GeodeticInformation? = GeodeticInformationTestFixtures.get(),
    ) = WanInfo(
        ip = ip,
        ispGeoInfo = ispGeoInfo,
    )
}