package com.soberg.netinfo.gradle.plugin

import com.soberg.netinfo.gradle.TestRunners
import com.soberg.netinfo.gradle.Versions
import com.soberg.netinfo.gradle.plugin.ext.android
import com.soberg.netinfo.gradle.plugin.ext.kotlinOptions
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.apply("kotlin-android")
        configureAndroid(project)
    }

    private fun configureAndroid(project: Project) = with(project) {
        with(android) {
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