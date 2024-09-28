package com.soberg.gradle.plugin.ext

import com.soberg.gradle.Versions
import org.gradle.api.Project

// See https://docs.gradle.org/current/userguide/toolchains.html
internal fun Project.configureJavaToolchain() {
    java {
        toolchain {
            languageVersion.set(Versions.Java.toolchainVersion)
        }
    }
}