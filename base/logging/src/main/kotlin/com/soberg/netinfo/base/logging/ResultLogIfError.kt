package com.soberg.netinfo.base.logging

fun <T> Result<T>.logErrorIfException(
    log: Logger,
    tag: String,
): Result<T> {
    if (isFailure) {
        val exception = exceptionOrNull()
        log.error(tag, exception?.message ?: "Error obtaining result", exception)
    }
    return this
}