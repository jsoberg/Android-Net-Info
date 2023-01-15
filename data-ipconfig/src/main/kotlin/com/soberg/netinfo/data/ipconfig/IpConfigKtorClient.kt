package com.soberg.netinfo.data.ipconfig

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
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