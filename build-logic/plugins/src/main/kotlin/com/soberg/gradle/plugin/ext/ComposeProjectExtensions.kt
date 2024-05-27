package com.soberg.gradle.plugin.ext

import org.gradle.api.Project
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

fun Project.composeCompiler(configure: ComposeCompilerGradlePluginExtension.() -> Unit) {
    val composeCompiler =
        extensions.findByName("composeCompiler") as ComposeCompilerGradlePluginExtension
    configure(composeCompiler)
}