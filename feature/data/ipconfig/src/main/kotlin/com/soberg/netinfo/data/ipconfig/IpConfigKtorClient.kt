package com.soberg.netinfo.data.ipconfig

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds

object IpConfigKtorClient {
    private val Timeout = 20.seconds

    fun create(
        engineFactory: HttpClientEngineFactory<HttpClientEngineConfig> = OkHttp,
    ) = HttpClient(engineFactory) { applyForIpConfig() }

    fun create(
        engine: HttpClientEngine,
    ) = HttpClient(engine) { applyForIpConfig() }

    private fun HttpClientConfig<*>.applyForIpConfig() {
        install(ContentNegotiation) {
            json(
                Json {
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