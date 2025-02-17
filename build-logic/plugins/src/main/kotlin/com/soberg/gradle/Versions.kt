package com.soberg.gradle

import org.gradle.api.JavaVersion
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

object Versions {

    object Android {
        object Sdk {
            const val min = 23
            const val compile = 35
            const val target = 35
        }
    }

    object Java {
        val sourceCompatibility = JavaVersion.VERSION_21
        val targetCompatibility = sourceCompatibility
        val toolchainVersion: JavaLanguageVersion =
            JavaLanguageVersion.of(sourceCompatibility.toString())
    }

    // See https://kotlinlang.org/docs/gradle-compiler-options.html#types-for-compiler-options
    object Kotlin {
        val jvmTarget = JvmTarget.JVM_21
        val languageVersion = KotlinVersion.KOTLIN_2_0
    }
}