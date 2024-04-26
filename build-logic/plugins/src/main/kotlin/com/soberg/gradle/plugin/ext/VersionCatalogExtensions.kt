package com.soberg.gradle.plugin.ext

import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency

// See https://discuss.gradle.org/t/using-version-catalogs-with-precompiled-script-plugins/45417/2
val Provider<PluginDependency>.pluginLibrary: String
    get() = map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }.get()