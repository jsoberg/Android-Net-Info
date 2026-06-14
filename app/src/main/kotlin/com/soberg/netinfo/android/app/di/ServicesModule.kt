package com.soberg.netinfo.android.app.di

import android.content.ClipboardManager
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class ServicesModule {

    companion object {
        @Provides
        @Singleton
        fun provideClipboardManager(
            @ApplicationContext context: Context,
        ): ClipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }
}