package com.soberg.gradle.plugin.ext

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

internal fun Project.kotlin(configure: KotlinJvmProjectExtension.() -> Unit) =
    extensions.configure("kotlin", configure)

internal fun Project.java(configure: JavaPluginExtension.() -> Unit) =
    extensions.configure("java", configure)

internal fun Project.plugins(configure: PluginContainer.() -> Unit) {
    configure(plugins)
}

internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")