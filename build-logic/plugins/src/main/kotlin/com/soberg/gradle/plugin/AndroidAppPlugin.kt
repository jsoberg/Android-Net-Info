package com.soberg.gradle.plugin

import com.soberg.gradle.Versions
import com.soberg.gradle.plugin.config.configureAndroidCommon
import com.soberg.gradle.plugin.ext.androidApp
import com.soberg.gradle.plugin.ext.configureJavaToolchain
import com.soberg.gradle.plugin.ext.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project

open class AndroidAppPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        plugins {
            apply("kotlin-android")
            apply("com.android.application")
            applyDependencyAnalysisPlugin()
        }
        configureJavaToolchain()
        androidApp {
            configureAndroidCommon()

            defaultConfig {
                targetSdk = Versions.Android.Sdk.Target
            }
        }
    }
}