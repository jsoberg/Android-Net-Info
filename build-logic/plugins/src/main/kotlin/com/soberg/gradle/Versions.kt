package com.soberg.gradle

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

object Versions {

    object Android {
        object Java {
            val sourceCompatibility = JavaVersion.VERSION_17
            val targetCompatibility = sourceCompatibility
            val jvmTarget = JvmTarget.JVM_17
        }

        object Sdk {
            const val min = 23
            const val compile = 34
            const val target = 33
        }
    }

    // See https://kotlinlang.org/docs/gradle-compiler-options.html#types-for-compiler-options
    object Kotlin {
        val jvmTarget = JvmTarget.JVM_17
        val languageVersion = KotlinVersion.KOTLIN_2_0
    }
}