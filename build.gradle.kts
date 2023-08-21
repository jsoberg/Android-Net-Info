plugins {
    id("convention.root")
    alias(libs.plugins.dependencyAnalysis)
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kover) apply false
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

        // TODO: Fixes dependency conflict issues with Dagger/Hilt, re: https://github.com/google/dagger/issues/3068. Once this issues is resolved, remove this dependency.
        classpath(libs.javaPoet)
    }
}

subprojects {
    apply(plugin = rootProject.libs.plugins.kover.get().pluginId)
}