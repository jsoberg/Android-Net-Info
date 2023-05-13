package com.soberg.netinfo.gradle.plugin

import com.soberg.netinfo.gradle.Versions
import com.soberg.netinfo.gradle.plugin.ext.kotlin
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinJvmLibraryModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project.plugins) {
            apply("org.jetbrains.kotlin.jvm")
            apply("java-library")
        }
        configureKotlin(project)
    }

    private fun configureKotlin(project: Project) = with(project) {
        kotlin {
            sourceSets.all {
                languageSettings {
                    languageVersion = Versions.Kotlin.languageVersion
                }
            }
        }
    }
}