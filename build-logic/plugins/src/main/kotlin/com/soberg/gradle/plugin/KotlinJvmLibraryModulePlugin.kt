package com.soberg.gradle.plugin

import com.soberg.gradle.Versions
import com.soberg.gradle.plugin.ext.configureJavaToolchain
import com.soberg.gradle.plugin.ext.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class KotlinJvmLibraryModulePlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        plugins {
            apply("org.jetbrains.kotlin.jvm")
            apply("java-library")
            applyDependencyAnalysisPlugin()
        }
        configureJavaToolchain()
        configureKotlin()
    }

    private fun Project.configureKotlin() {
        tasks.withType<KotlinCompile>().configureEach {
            compilerOptions {
                languageVersion.set(Versions.Kotlin.languageVersion)
            }
        }
    }
}