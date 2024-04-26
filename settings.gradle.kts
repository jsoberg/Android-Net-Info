pluginManagement {
    includeBuild("build-logic")

    repositories {
        gradlePluginPortal()
        google()
    }
}

plugins {
    id("local.root.settings")
}

rootProject.name = "android-net-info"

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

