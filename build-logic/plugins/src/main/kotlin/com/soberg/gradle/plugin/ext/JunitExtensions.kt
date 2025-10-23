package com.soberg.gradle.plugin.ext

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.configureJunitJupiter() {
    dependencies {
        val bom = platform(libs.findLibrary("test-junitJupiter-bom").get())
        testImplementation(bom)
        testImplementation(libs.findLibrary("test-junitJupiter").get())
        testRuntimeOnly(libs.findLibrary("test-junitJupiter-launcher").get())
    }
}