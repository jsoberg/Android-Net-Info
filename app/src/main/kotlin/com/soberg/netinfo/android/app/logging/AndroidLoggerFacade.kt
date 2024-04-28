package com.soberg.netinfo.android.app.logging

import android.util.Log
import com.soberg.netinfo.base.logging.Logger

object AndroidLoggerFacade : Logger.Facade {
    override fun log(
        level: Logger.Facade.Level,
        tag: String,
        message: String,
        throwable: Throwable?
    ) {
        when (level) {
            Logger.Facade.Level.Verbose -> Log.v(tag, message, throwable)
            Logger.Facade.Level.Info -> Log.i(tag, message, throwable)
            Logger.Facade.Level.Debug -> Log.d(tag, message, throwable)
            Logger.Facade.Level.Warn -> Log.w(tag, message, throwable)
            Logger.Facade.Level.Error -> Log.e(tag, message, throwable)
        }
    }
}