package com.soberg.netinfo.data.ipconfig

import com.soberg.netinfo.base.annotation.IODispatcher
import com.soberg.netinfo.base.logging.Logger
import com.soberg.netinfo.data.ipconfig.model.WanInfoNetworkModel
import com.soberg.netinfo.data.ipconfig.model.toDomain
import com.soberg.netinfo.domain.wan.WanInfoRepository
import com.soberg.netinfo.domain.wan.model.WanInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IpConfigWanInfoRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val clientProvider: HttpClientProvider,
) : WanInfoRepository {

    companion object {
        private const val Tag = "IpConfigWanInfoRepository"
    }

    override suspend fun loadWanInfo(): WanInfoRepository.Result = withContext(ioDispatcher) {
        runCatching {
            // Note: This client would normally be reused, but here we always want the client to use the latest active network interface.
            clientProvider().use { client ->
                runQuery(client)
            }
        }.fold(
            onSuccess = { info ->
                Logger.debug(Tag, "Query to ipconfig.io succeeded, IP ${info.ip}")
                WanInfoRepository.Result.Success(info)
            },
            onFailure = { error ->
                Logger.warn(Tag, "Query to ipconfig.io failed", error)
                WanInfoRepository.Result.Error
            },
        )
    }

    private suspend fun runQuery(client: HttpClient): WanInfo {
        val response = client.get(IpConfigUrl.Main)
        return if (response.status == HttpStatusCode.OK) {
            response.body<WanInfoNetworkModel>()
                .toDomain()
                .getOrThrow()
        } else {
            error("Received status code ${response.status}")
        }
    }

    fun interface HttpClientProvider : () -> HttpClient

    class KtorHttpClientProvider : HttpClientProvider {
        override fun invoke(): HttpClient = IpConfigKtorClient.create()
    }
}