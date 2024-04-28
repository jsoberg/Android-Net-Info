package com.soberg.netinfo.base.logging

import java.util.concurrent.atomic.AtomicReference

object Logger {

    private val facadeRef = AtomicReference(Facade.Default)

    fun plantFacade(facade: Facade) {
        facadeRef.set(facade)
    }

    fun verbose(
        tag: String,
        message: String,
        throwable: Throwable? = null,
    ) {
        facadeRef.get().log(
            level = Facade.Level.Verbose,
            tag = tag,
            message = message,
            throwable = throwable,
        )
    }

    fun info(
        tag: String,
        message: String,
        throwable: Throwable? = null,
    ) {
        facadeRef.get().log(
            level = Facade.Level.Info,
            tag = tag,
            message = message,
            throwable = throwable,
        )
    }

    fun debug(
        tag: String,
        message: String,
        throwable: Throwable? = null,
    ) {
        facadeRef.get().log(
            level = Facade.Level.Debug,
            tag = tag,
            message = message,
            throwable = throwable,
        )
    }

    fun warn(
        tag: String,
        message: String,
        throwable: Throwable? = null,
    ) {
        facadeRef.get().log(
            level = Facade.Level.Warn,
            tag = tag,
            message = message,
            throwable = throwable,
        )
    }

    fun error(
        tag: String,
        message: String,
        throwable: Throwable? = null,
    ) {
        facadeRef.get().log(
            level = Facade.Level.Error,
            tag = tag,
            message = message,
            throwable = throwable,
        )
    }

    interface Facade {
        fun log(
            level: Level,
            tag: String,
            message: String,
            throwable: Throwable? = null,
        )

        enum class Level {
            Verbose,
            Info,
            Debug,
            Warn,
            Error,
        }

        companion object {
            val Default = object : Facade {
                override fun log(
                    level: Level,
                    tag: String,
                    message: String,
                    throwable: Throwable?
                ) {
                    when (level) {
                        Level.Verbose,
                        Level.Info,
                        Level.Debug,
                        Level.Warn,
                        -> {
                            println("$tag: $message")
                            throwable?.printStackTrace(System.out)
                        }

                        Level.Error -> {
                            System.err.println("$tag: $message")
                            throwable?.printStackTrace(System.err)
                        }
                    }
                }
            }
        }
    }
}