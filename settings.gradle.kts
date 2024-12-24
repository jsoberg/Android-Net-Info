pluginManagement {
    includeBuild("build-logic")

    repositories {
        gradlePluginPortal()
        google()
    }
}

plugins {
    id("local.root.settings")
    // Can't apply these version from the TOML, since they're applied in settings before the TOML is ready to use.

    // See https://plugins.gradle.org/plugin/org.gradle.toolchains.foojay-resolver-convention
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
    // See https://kotlin.github.io/kotlinx-kover/gradle-plugin/aggregated.html
    id("org.jetbrains.kotlinx.kover.aggregation") version "0.9.0"
}

kover {
    enableCoverage()

    reports {
        excludedClasses.addAll(
            "*Module",
            "*TestFixtures",
        )

        excludedProjects.addAll(
            ":base:annotations",
            ":test:coroutine-test-ext",
        )

        excludesAnnotatedBy.addAll(
            // Dagger/Hilt
            "*DaggerGenerated*",
            "*Generated*",
            "*Module*",
            "*Provides*",
            // Compose previews
            "*Preview*",
            "*A11yPreview*",
        )
    }
}

include(":app")
include(":base:annotations")
include(":base:logging")
include(":base:types")
include(":feature:data:ipconfig")
include(":feature:data:network-connectivity")
include(":feature:domain")
include(":feature:resources:drawables")
include(":feature:resources:lottie")
include(":feature:resources:strings")
include(":feature:ui")
include(":infra:android-services")
include(":infra:compose-ext")
include(":infra:viewmodel-ext")
include(":test:coroutines-test-ext")

