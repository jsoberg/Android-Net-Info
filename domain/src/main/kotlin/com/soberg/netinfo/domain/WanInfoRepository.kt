package com.soberg.netinfo.domain

import com.soberg.netinfo.domain.model.WanInfo

interface WanInfoRepository {

    suspend fun loadWanInfo() : Result

    sealed interface Result {
        data class Success(
            val wanInfo: WanInfo,
        ) : Result

        object Error : Result
    }
}