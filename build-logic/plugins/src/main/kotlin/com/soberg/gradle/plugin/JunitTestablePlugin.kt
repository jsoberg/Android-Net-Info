package com.soberg.gradle.plugin

import com.soberg.gradle.plugin.ext.configureJunitJupiter
import org.gradle.api.Plugin
import org.gradle.api.Project

class JunitTestablePlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        configureJunitJupiter()
    }
}