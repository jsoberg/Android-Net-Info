package com.soberg.netinfo.base.logging

interface Logger {
    fun verbose(
        tag: String,
        message: String,
        throwable: Throwable? = null,
    )

    fun info(
        tag: String,
        message: String,
        throwable: Throwable? = null,
    )

    fun debug(
        tag: String,
        message: String,
        throwable: Throwable? = null,
    )

    fun warning(
        tag: String,
        message: String,
        throwable: Throwable? = null,
    )

    fun error(
        tag: String,
        message: String,
        throwable: Throwable? = null,
    )
}