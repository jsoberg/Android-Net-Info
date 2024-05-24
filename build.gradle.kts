plugins {
    alias(libs.plugins.dependencyAnalysis)
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kover) apply false
    alias(libs.plugins.ksp) apply false

    id("local.root.project")
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.hilt.agp)
        classpath(libs.kotlin.gradlePlugin)
    }
}

subprojects {
    apply(plugin = rootProject.libs.plugins.kover.get().pluginId)
}