package com.soberg.netinfo.data.ipconfig

import com.soberg.netinfo.base.annotation.IODispatcher
import com.soberg.netinfo.base.logging.Logger
import com.soberg.netinfo.data.ipconfig.model.WanInfoNetworkModel
import com.soberg.netinfo.data.ipconfig.model.toDomain
import com.soberg.netinfo.domain.wan.WanInfoRepository
import com.soberg.netinfo.domain.wan.model.WanInfo
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class IpConfigWanInfoRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val query: HttpQuery,
) : WanInfoRepository {

    companion object {
        private const val Tag = "IpConfigWanInfoRepository"
        private val UnknownHostTimeout = 1.seconds
    }

    override suspend fun loadWanInfo(): WanInfoRepository.Result = withContext(ioDispatcher) {
        runCatching {
            runQuery(query, isUnknownHostRetry = false)
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

    private suspend fun runQuery(
        query: HttpQuery,
        isUnknownHostRetry: Boolean,
    ): WanInfo {
        val response = runCatching {
            queryForResponse(query, isUnknownHostRetry)
        }.fold(
            onSuccess = { it },
            onFailure = { error ->
                if (!isUnknownHostRetry && (error is UnknownHostException || error is ConnectException)) {
                    // We've received a connection exception even though we appear to be active, retry.
                    Logger.debug(Tag, "Retrying ipconfig.io query...")
                    queryForResponse(query, isBadConnectionRetry = true)
                } else {
                    error("Unable to retrieve WAN info")
                }
            },
        )

        return if (response.status == HttpStatusCode.OK) {
            response.body<WanInfoNetworkModel>()
                .toDomain()
                .getOrThrow()
        } else {
            error("Unable to retrieve WAN info, status code ${response.status}")
        }
    }

    private suspend fun queryForResponse(
        query: HttpQuery,
        isBadConnectionRetry: Boolean,
    ): HttpResponse {
        // Slight delay to allow time for connection to be made/make sure we're not throttling ipconfig.io
        if (isBadConnectionRetry) {
            delay(UnknownHostTimeout)
        }

        return query(IpConfigUrl.Main)
    }

    fun interface HttpQuery : suspend (String) -> HttpResponse

    class KtorHttpQuery : HttpQuery {
        override suspend fun invoke(url: String): HttpResponse {
            // Note: This client would normally be reused, but here we always want the client to use the latest active network interface.
            IpConfigKtorClient.create().use { client ->
                return client.get(url)
            }
        }
    }
}