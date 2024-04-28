package com.soberg.netinfo.android.app.di

import com.soberg.netinfo.data.ipconfig.IpConfigWanInfoRepository
import com.soberg.netinfo.domain.wan.WanInfoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class IpConfigModule {
    companion object {
        @Provides
        @Singleton
        internal fun provideHttpQuery(): IpConfigWanInfoRepository.HttpQuery =
            IpConfigWanInfoRepository.KtorHttpQuery()
    }

    @Binds
    internal abstract fun provideWanInfoRepository(repo: IpConfigWanInfoRepository): WanInfoRepository
}