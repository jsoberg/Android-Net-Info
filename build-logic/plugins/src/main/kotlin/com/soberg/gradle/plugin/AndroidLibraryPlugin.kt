package com.soberg.gradle.plugin

import com.soberg.gradle.plugin.config.configureAndroidCommon
import com.soberg.gradle.plugin.ext.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project

open class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        plugins {
            apply("kotlin-android")
            apply("com.android.library")
        }
        configureAndroidCommon()
    }

}