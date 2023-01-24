package com.soberg.netinfo.gradle.plugin

import Plugins
import TestRunners
import Versions
import com.soberg.netinfo.gradle.plugin.ext.android
import com.soberg.netinfo.gradle.plugin.ext.kotlinOptions
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.apply(Plugins.Kotlin.Android)
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
                allWarningsAsErrors = true
            }

            compileSdkVersion(Versions.Android.Sdk.compile)
            defaultConfig {
                minSdk = Versions.Android.Sdk.min
                targetSdk = Versions.Android.Sdk.target
                testInstrumentationRunner = TestRunners.androidJUnit
            }
        }
    }


}