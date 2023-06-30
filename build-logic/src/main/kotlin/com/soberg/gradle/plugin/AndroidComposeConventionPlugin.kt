package com.soberg.gradle.plugin

import com.soberg.gradle.plugin.ext.android
import com.soberg.gradle.plugin.ext.androidTestImplementation
import com.soberg.gradle.plugin.ext.implementation
import com.soberg.gradle.plugin.ext.libs
import com.soberg.gradle.plugin.ext.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidComposeConventionPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        configureCompose()
    }

    private fun Project.configureCompose() {

        android {
            buildFeatures.compose = true
            composeOptions.kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
        }

        dependencies {
            val bom = platform(libs.compose.bom)
            implementation(bom)
            androidTestImplementation(bom)
            testImplementation(bom)
        }
    }
}