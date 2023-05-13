package com.soberg.netinfo.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class DependenciesPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        /* No-op: this plugin only exists to bring in values from this build-plugins module. */
    }
}