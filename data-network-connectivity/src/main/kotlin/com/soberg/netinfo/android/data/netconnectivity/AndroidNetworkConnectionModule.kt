package com.soberg.netinfo.android.data.netconnectivity

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import com.soberg.netinfo.android.data.netconnectivity.local.FindLocalIpAddressUseCase
import com.soberg.netinfo.android.data.netconnectivity.local.GetNetworkInterfaceByNameUseCase
import com.soberg.netinfo.domain.lan.NetworkConnectionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.net.NetworkInterface
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class AndroidNetworkConnectionModule {
    companion object {
        @Provides
        @Singleton
        internal fun provideConnectivityManager(@ApplicationContext appContext: Context): ConnectivityManager =
            getSystemService(appContext, ConnectivityManager::class.java) as ConnectivityManager

        @Provides
        internal fun providesGetNetworkInterfaceByNameUseCase(): GetNetworkInterfaceByNameUseCase =
            object : GetNetworkInterfaceByNameUseCase {
                override fun invoke(interfaceName: String): NetworkInterface? =
                    NetworkInterface.getByName(interfaceName)
            }

        @Provides
        internal fun providesFindLocalIpAddressUseCase(
            getNetworkInterfaceByName: GetNetworkInterfaceByNameUseCase,
        ): FindLocalIpAddressUseCase = FindLocalIpAddressUseCase(
            getNetworkInterfaceByName = getNetworkInterfaceByName
        )
    }

    @Binds
    internal abstract fun provideConnectionRepository(repository: AndroidNetworkConnectionRepository): NetworkConnectionRepository
}