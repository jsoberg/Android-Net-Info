package com.soberg.gradle.plugin

import com.soberg.gradle.TestRunners
import com.soberg.gradle.Versions
import com.soberg.gradle.plugin.ext.android
import com.soberg.gradle.plugin.ext.kotlinOptions
import com.soberg.gradle.plugin.ext.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project

open class AndroidModulePlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        plugins {
            apply("kotlin-android")
        }
        configureAndroid()
    }

    private fun Project.configureAndroid() {
        android {
            compileOptions {
                sourceCompatibility = Versions.Android.Java.sourceCompatibility
                targetCompatibility = Versions.Android.Java.targetCompatibility
            }

            kotlinOptions {
                jvmTarget = Versions.Kotlin.jvmTarget
                allWarningsAsErrors = false
            }

            compileSdkVersion(Versions.Android.Sdk.compile)
            defaultConfig {
                minSdk = Versions.Android.Sdk.min
                targetSdk = Versions.Android.Sdk.target
                testInstrumentationRunner = TestRunners.androidJUnit
            }

            testOptions.unitTests.isIncludeAndroidResources = true
        }
    }

}