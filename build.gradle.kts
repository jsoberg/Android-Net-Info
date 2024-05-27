plugins {
    alias(libs.plugins.dependencyAnalysis)

    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false

    id("local.root.project")
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}