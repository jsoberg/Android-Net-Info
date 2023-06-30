import com.soberg.gradle.KotlinCompilerArgs
import com.soberg.gradle.Versions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("deps")
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

allprojects {
    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = Versions.Kotlin.jvmTarget
            freeCompilerArgs = freeCompilerArgs + KotlinCompilerArgs
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
