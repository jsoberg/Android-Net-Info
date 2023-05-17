package com.soberg.netinfo.gradle

import com.soberg.netinfo.gradle.Versions.Android.Java.targetCompatibility
import org.gradle.api.JavaVersion

object Versions {

    object Android {
        object Java {
            val sourceCompatibility = JavaVersion.VERSION_17
            val targetCompatibility = sourceCompatibility
        }

        object Sdk {
            const val min = 23
            const val compile = 33
            const val target = 33
        }
    }

    object Kotlin {
        val jvmTarget = targetCompatibility.toString()
        const val languageVersion = "1.8"
    }
}