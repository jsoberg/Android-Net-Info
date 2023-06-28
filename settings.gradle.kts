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
include(":base:types")
include(":feature:data:ipconfig")
include(":feature:data:network-connectivity")
include(":feature:domain")
include(":feature:ui")
include(":infra:android-services")
include(":infra:viewmodel-ext")

