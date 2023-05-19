package com.soberg.netinfo.gradle.plugin

import com.soberg.netinfo.gradle.plugin.ext.android
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidComposeConventionPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        configureCompose()
    }

    private fun Project.configureCompose() {
        val libs = extensions.getByType<VersionCatalogsExtension>()
            .named("libs")

        android {
            buildFeatures.compose = true
            val composeCompilerVersion = libs.findVersion("composeCompiler").get().toString()
            composeOptions.kotlinCompilerExtensionVersion = composeCompilerVersion
        }

        dependencies {
            val bom = libs.findLibrary("compose-bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))
            add("testImplementation", platform(bom))
        }
    }
}