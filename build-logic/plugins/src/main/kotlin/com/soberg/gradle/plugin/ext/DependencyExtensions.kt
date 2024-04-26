package com.soberg.gradle.plugin.ext

import org.gradle.api.internal.catalog.DelegatingProjectDependency
import org.gradle.kotlin.dsl.DependencyHandlerScope

internal fun DependencyHandlerScope.implementation(dependencyNotation: Any) =
    add("implementation", dependencyNotation)

internal fun DependencyHandlerScope.testImplementation(dependencyNotation: Any) =
    add("testImplementation", dependencyNotation)

internal fun DependencyHandlerScope.androidTestImplementation(dependencyNotation: Any) =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandlerScope.koverImplementation(projectDep: DelegatingProjectDependency) {
    add("kover", projectDep)
    implementation(projectDep)
}