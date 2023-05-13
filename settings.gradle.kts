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
include(":base-annotations")
include(":base-types")
include(":data-ipconfig")
include(":data-network-connectivity")
include(":domain")
include(":infra-viewmodel-ext")
include(":ui")
