package com.soberg.gradle.plugin

import com.soberg.gradle.plugin.ext.android
import com.soberg.gradle.plugin.ext.androidTestImplementation
import com.soberg.gradle.plugin.ext.implementation
import com.soberg.gradle.plugin.ext.libs
import com.soberg.gradle.plugin.ext.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


class AndroidComposeConventionPlugin : Plugin<Project> {

    private companion object {
        private const val ComposeCompilerPluginPrepend =
            "plugin:androidx.compose.compiler.plugins.kotlin"
    }

    override fun apply(project: Project) = with(project) {
        configureCompose()
        configureComposeMetrics()
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

    private fun Project.configureComposeMetrics() {
        val composeMetricsDir = layout.buildDirectory.dir("compose-metrics").get().asFile
        val composeReportsDir = layout.buildDirectory.dir("compose-reports").get().asFile

        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-P",
                    "$ComposeCompilerPluginPrepend:metricsDestination=${composeMetricsDir.absolutePath}",

                    "-P",
                    "$ComposeCompilerPluginPrepend:reportsDestination=${composeReportsDir.absolutePath}",
                )
            }
        }
    }
}