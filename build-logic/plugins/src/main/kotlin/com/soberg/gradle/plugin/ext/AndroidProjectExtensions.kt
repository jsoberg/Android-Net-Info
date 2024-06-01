package com.soberg.gradle.plugin.ext

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

internal fun Project.androidLibrary(configure: LibraryExtension.() -> Unit) {
    extensions.findByType<LibraryExtension>()?.configure()
        ?: error("Module ${this.name} cannot find Android library extension")
}

internal fun Project.androidApp(configure: ApplicationExtension.() -> Unit) {
    extensions.findByType<ApplicationExtension>()?.configure()
        ?: error("Module ${this.name} cannot find Android application extension")
}

internal fun Project.androidCommon(configure: AndroidCommonExtension.() -> Unit) {
    getAndroidCommonExtension().configure()
}

internal fun Project.getAndroidCommonExtension(): AndroidCommonExtension {
    extensions.findByType<LibraryExtension>()?.let { lib ->
        // We're an Android library
        return lib
    } ?: run {
        extensions.findByType<ApplicationExtension>()?.let { app ->
            // We're an Android application
            return app
        } ?: error("Module ${this.name} cannot find Android library or application extension")
    }
}

internal fun Project.kotlinAndroid(configure: KotlinAndroidProjectExtension.() -> Unit) {
    extensions.getByType<KotlinAndroidProjectExtension>().configure()
}

internal typealias AndroidCommonExtension = CommonExtension<*, *, *, *, *, *>