package com.soberg.netinfo.data.ipconfig

import com.soberg.netinfo.data.ipconfig.model.WanInfoNetworkModel
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class IpConfigKtorClientTest {

    private val json = Json {
        prettyPrint = true
    }

    @Disabled("Only used to test response from ipconfig.io")
    @Test
    fun `ipconfig integration test`() = runTest {
        val client = IpConfigKtorClient.create(OkHttp)
        with(client.get(IpConfigUrl.Main)) {
            println("Response status code: $status")
            if (status == HttpStatusCode.OK) {
                val networkModel = body<WanInfoNetworkModel>()
                val stringRep = json.encodeToString(networkModel)
                println(stringRep)
            }
        }
    }

}