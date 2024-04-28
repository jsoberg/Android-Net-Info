package com.soberg.netinfo.base.logging

fun <T> Result<T>.logErrorIfException(
    tag: String,
    message: String = exceptionOrNull()?.message ?: "Error obtaining result"
): Result<T> {
    if (isFailure) {
        Logger.error(tag, message, exceptionOrNull())
    }
    return this
}