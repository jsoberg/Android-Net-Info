package com.soberg.gradle.plugin

import com.soberg.gradle.plugin.ext.androidCommon
import com.soberg.gradle.plugin.ext.androidTestImplementation
import com.soberg.gradle.plugin.ext.composeCompiler
import com.soberg.gradle.plugin.ext.implementation
import com.soberg.gradle.plugin.ext.libs
import com.soberg.gradle.plugin.ext.plugins
import com.soberg.gradle.plugin.ext.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidComposePlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        plugins {
            apply("org.jetbrains.kotlin.plugin.compose")
        }
        configureCompose()
        configureComposeMetrics()
    }

    private fun Project.configureCompose() {
        androidCommon {
            buildFeatures.compose = true
        }

        dependencies {
            val bom = platform(libs.findLibrary("compose-bom").get())
            implementation(bom)
            androidTestImplementation(bom)
            testImplementation(bom)
        }
    }

    private fun Project.configureComposeMetrics() {
        composeCompiler {
            if (project.providers.gradleProperty("composeCompilerReports").orNull == "true") {
                val composeReportsDir = layout.buildDirectory.dir("compose-compiler").get().asFile
                metricsDestination.set(composeReportsDir)
                reportsDestination.set(composeReportsDir)
            }
        }
    }
}