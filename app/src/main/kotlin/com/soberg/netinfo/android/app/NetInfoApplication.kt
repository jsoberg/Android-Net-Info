package com.soberg.netinfo.android.app

import android.app.Application
import com.soberg.netinfo.android.app.logging.AndroidLoggerFacade
import com.soberg.netinfo.base.logging.Logger
import dagger.hilt.android.HiltAndroidApp
import kotlin.time.DurationUnit
import kotlin.time.measureTime

@HiltAndroidApp
class NetInfoApplication : Application() {

    companion object {
        private const val Tag = "NetInfoApplication"
    }

    override fun onCreate() {
        val onCreateTime = measureTime {
            Logger.plantFacade(AndroidLoggerFacade)
            Logger.debug(Tag, "Application onCreate Started")
            Logger.debug(Tag, "-- Logging Facade planted")
            super.onCreate()
        }

        Logger.debug(
            Tag,
            "Application onCreate Finished in ${
                onCreateTime.toString(
                    DurationUnit.SECONDS,
                    decimals = 3
                )
            }"
        )
    }
}