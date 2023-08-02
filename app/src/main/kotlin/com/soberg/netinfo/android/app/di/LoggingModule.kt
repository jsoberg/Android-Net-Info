package com.soberg.netinfo.android.app.di

import com.soberg.netinfo.android.app.logging.AndroidLogger
import com.soberg.netinfo.base.logging.Logger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class LoggingModule {

    @Binds
    internal abstract fun provideAndroidLogger(logger: AndroidLogger): Logger
}