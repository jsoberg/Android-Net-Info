package com.soberg.netinfo.base.type.network

@JvmInline
value class IpAddress(val value: String) {
    override fun toString(): String = value
}