package com.soberg.netinfo.data.ipconfig

import com.soberg.netinfo.base.annotation.IODispatcher
import com.soberg.netinfo.data.ipconfig.model.WanInfoNetworkModel
import com.soberg.netinfo.data.ipconfig.model.toDomain
import com.soberg.netinfo.domain.wan.WanInfoRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class IpConfigWanInfoRepository(
    private val client: HttpClient,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) : WanInfoRepository {

    override suspend fun loadWanInfo(): WanInfoRepository.Result = withContext(ioDispatcher) {
        runCatching {
            val response = client.get(IpConfigUrl.Main)
            if (response.status == HttpStatusCode.OK) {
                response.body<WanInfoNetworkModel>()
                    .toDomain()
                    .getOrThrow()
            } else {
                error("Received status code ${response.status}")
            }
        }.fold(
            onSuccess = WanInfoRepository.Result::Success,
            onFailure = { WanInfoRepository.Result.Error },
        )
    }

}