package com.soberg.netinfo.gradle.plugin.ext

import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

fun Project.kotlin(configure: KotlinJvmProjectExtension.() -> Unit) =
    extensions.configure("kotlin", configure)

fun Project.plugins(configure: PluginContainer.() -> Unit) {
    configure(plugins)
}