package com.soberg.netinfo.gradle.plugin.ext

import org.gradle.api.Action
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

internal fun Project.kotlin(configure: Action<KotlinJvmProjectExtension>) =
        extensions.configure("kotlin", configure)