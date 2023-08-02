package com.soberg.netinfo.data.ipconfig

import com.soberg.netinfo.base.annotation.IODispatcher
import com.soberg.netinfo.base.logging.Logger
import com.soberg.netinfo.base.logging.logErrorIfException
import com.soberg.netinfo.data.ipconfig.model.WanInfoNetworkModel
import com.soberg.netinfo.data.ipconfig.model.toDomain
import com.soberg.netinfo.domain.wan.WanInfoRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IpConfigWanInfoRepository @Inject constructor(
    private val client: HttpClient,
    private val log: Logger,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) : WanInfoRepository {

    companion object {
        private const val Tag = "IpConfigWanInfoRepository"
    }

    override suspend fun loadWanInfo(): WanInfoRepository.Result = withContext(ioDispatcher) {
        runCatching {
            val response = client.get(IpConfigUrl.Main)
            if (response.status == HttpStatusCode.OK) {
                log.debug(Tag, "Query to ipconfig.io succeeded")
                response.body<WanInfoNetworkModel>()
                    .toDomain()
                    .logErrorIfException(log, Tag)
                    .getOrThrow()
            } else {
                log.debug(Tag, "Query to ipconfig.io failed")
                error("Received status code ${response.status}")
            }
        }.fold(
            onSuccess = WanInfoRepository.Result::Success,
            onFailure = { WanInfoRepository.Result.Error },
        )
    }

}