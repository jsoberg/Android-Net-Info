package com.soberg.netinfo.domain.wan

import com.soberg.netinfo.domain.wan.model.WanInfo

interface WanInfoRepository {

    suspend fun loadWanInfo(): Result

    sealed interface Result {
        data class Success(
            val wanInfo: WanInfo,
        ) : Result

        object Error : Result
    }
}