package com.soberg.netinfo.android.app.logging

import android.util.Log
import com.soberg.netinfo.base.logging.Logger
import javax.inject.Inject

class AndroidLogger @Inject constructor() : Logger {
    override fun verbose(tag: String, message: String, throwable: Throwable?) {
        Log.v(tag, message, throwable)
    }

    override fun info(tag: String, message: String, throwable: Throwable?) {
        Log.i(tag, message, throwable)
    }

    override fun debug(tag: String, message: String, throwable: Throwable?) {
        Log.d(tag, message, throwable)
    }

    override fun warning(tag: String, message: String, throwable: Throwable?) {
        Log.w(tag, message, throwable)
    }

    override fun error(tag: String, message: String, throwable: Throwable?) {
        Log.e(tag, message, throwable)
    }
}