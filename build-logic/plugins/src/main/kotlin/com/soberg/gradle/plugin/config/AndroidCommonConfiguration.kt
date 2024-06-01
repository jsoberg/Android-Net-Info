package com.soberg.gradle.plugin.config

import com.soberg.gradle.TestRunners
import com.soberg.gradle.Versions
import com.soberg.gradle.plugin.ext.androidCommon
import com.soberg.gradle.plugin.ext.kotlinAndroid
import org.gradle.api.Project

internal fun Project.configureAndroidCommon() {
    androidCommon {
        compileOptions {
            sourceCompatibility = Versions.Android.Java.sourceCompatibility
            targetCompatibility = Versions.Android.Java.targetCompatibility
        }

        kotlinAndroid {
            compilerOptions {
                jvmTarget.set(Versions.Android.Java.jvmTarget)
                languageVersion.set(Versions.Kotlin.languageVersion)
                allWarningsAsErrors.set(false)
            }
        }

        compileSdk = Versions.Android.Sdk.compile
        defaultConfig {
            minSdk = Versions.Android.Sdk.min
            testInstrumentationRunner = TestRunners.androidJUnit
        }

        testOptions.unitTests.isIncludeAndroidResources = true
    }
}