package com.soberg.gradle.plugin

import com.soberg.gradle.KotlinCompilerArgs
import com.soberg.gradle.Versions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Delete
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class RootPlugin : Plugin<Project> {

    companion object {
        private const val CleanTaskName = "clean"
    }

    override fun apply(project: Project) = with(project) {
        addRootCleanTask()

        allprojects {
            applyTestOptions()
            applyKotlinCompileOptions()
        }
    }

    private fun Project.applyTestOptions() {
        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }

    private fun Project.applyKotlinCompileOptions() {
        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = Versions.Kotlin.jvmTarget
                freeCompilerArgs = freeCompilerArgs + KotlinCompilerArgs
            }
        }
    }

    private fun Project.addRootCleanTask() {
        tasks.register(CleanTaskName, Delete::class) {
            delete(rootProject.buildDir)
        }
    }
}