package com.soberg.gradle.plugin

import org.gradle.api.plugins.PluginContainer

fun PluginContainer.applyDependencyAnalysisPlugin() {
    apply("com.autonomousapps.dependency-analysis")
}