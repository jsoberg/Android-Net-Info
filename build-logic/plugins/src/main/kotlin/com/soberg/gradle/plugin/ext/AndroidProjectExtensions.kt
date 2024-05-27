package com.soberg.gradle.plugin.ext

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.ExtensionContainer
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

fun Project.android(configure: BaseExtension.() -> Unit) {
    val android = extensions.findByName("android") as BaseExtension
    configure(android)
}

fun Project.composeCompiler(configure: ComposeCompilerGradlePluginExtension.() -> Unit) {
    val android = extensions.findByName("composeCompiler") as ComposeCompilerGradlePluginExtension
    configure(android)
}

fun BaseExtension.kotlinOptions(configure: KotlinJvmOptions.() -> Unit) =
    androidExtensions.configure("kotlinOptions", configure)

private val BaseExtension.androidExtensions: ExtensionContainer
    get() = (this as ExtensionAware).extensions