package com.soberg.netinfo.data.ipconfig

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds

internal object IpConfigKtorClient {
    const val Url = "https://ipconfig.io"

    private val Timeout = 20.seconds

    fun create(): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        encodeDefaults = true
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
            install(HttpTimeout) {
                socketTimeoutMillis = Timeout.inWholeMilliseconds
                requestTimeoutMillis = Timeout.inWholeMilliseconds
                connectTimeoutMillis = Timeout.inWholeMilliseconds
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }
}