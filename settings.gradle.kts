enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

pluginManagement {
    includeBuild("build-logic")
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

