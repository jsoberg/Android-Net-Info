package com.soberg.gradle.plugin

import com.soberg.gradle.Names
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.api.initialization.resolve.RepositoriesMode.FAIL_ON_PROJECT_REPOS

class RootSettingsPlugin : Plugin<Settings> {
    override fun apply(target: Settings) = with(target) {
        enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
        dependencyResolutionManagement {
            repositoriesMode.set(FAIL_ON_PROJECT_REPOS)

            repositories {
                google {
                    content {
                        includeGroupByRegex("com\\.android\\..*")
                        includeGroupByRegex("com\\.google\\..*")
                        includeGroupByRegex("androidx\\..*")
                    }
                }
                mavenCentral()
            }
        }

        rootProject.name = Names.RootProject
    }
}