package com.soberg.gradle.plugin

import com.soberg.gradle.Versions
import com.soberg.gradle.plugin.ext.kotlin
import com.soberg.gradle.plugin.ext.plugins
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinJvmLibraryModulePlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        plugins {
            apply("org.jetbrains.kotlin.jvm")
            apply("java-library")
        }
        configureKotlin()
    }

    private fun Project.configureKotlin() {
        kotlin {
            sourceSets.all {
                languageSettings {
                    languageVersion = Versions.Kotlin.languageVersion
                }
            }
        }
    }
}