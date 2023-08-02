package com.soberg.netinfo.android.app.di

import com.soberg.netinfo.data.ipconfig.IpConfigWanInfoRepository
import com.soberg.netinfo.domain.wan.WanInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*

@InstallIn(SingletonComponent::class)
@Module
internal abstract class IpConfigModule {

    @Binds
    internal abstract fun provideWanInfoRepository(repo: IpConfigWanInfoRepository): WanInfoRepository
}