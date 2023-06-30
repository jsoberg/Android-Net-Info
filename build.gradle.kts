plugins {
    id("convention.root")
    alias(libs.plugins.dependencyAnalysis)
    alias(libs.plugins.hilt) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.hilt.agp)

        // TODO: Fixes dependency conflict issues with Dagger/Hilt, re: https://github.com/google/dagger/issues/3068. Once this issues is resolved, remove this dependency.
        classpath(libs.javaPoet)
    }
}