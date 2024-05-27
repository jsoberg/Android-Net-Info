package com.soberg.gradle.plugin.ext

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

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

fun AndroidCommonExtension.kotlinOptions(configure: KotlinJvmOptions.() -> Unit) =
    androidExtensions.configure("kotlinOptions", configure)

private val AndroidCommonExtension.androidExtensions: ExtensionContainer
    get() = (this as ExtensionAware).extensions

internal typealias AndroidCommonExtension = CommonExtension<*, *, *, *, *, *>