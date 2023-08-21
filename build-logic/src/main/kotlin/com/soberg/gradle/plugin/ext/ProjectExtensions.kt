package com.soberg.gradle.plugin.ext

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

internal fun Project.kotlin(configure: KotlinJvmProjectExtension.() -> Unit) =
    extensions.configure("kotlin", configure)

internal fun Project.plugins(configure: PluginContainer.() -> Unit) {
    configure(plugins)
}

internal val Project.libs: LibrariesForLibs
    get() = project.the()