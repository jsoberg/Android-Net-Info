enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

include(":app")
include(":base-annotations")
include(":base-types")
include(":data-ipconfig")
include(":domain")
include(":ui")
