pluginManagement {
    includeBuild("build-logic")

    repositories {
        gradlePluginPortal()
        google()
    }
}

plugins {
    id("local.root.settings")
    // Can't apply this version from the TOML, since this is applied in settings before the TOML is ready to use.
    // See https://plugins.gradle.org/plugin/org.gradle.toolchains.foojay-resolver-convention
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
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

