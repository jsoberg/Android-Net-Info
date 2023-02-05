enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

includeBuild("build-plugins")

include(":app")
include(":base-annotations")
include(":base-types")
include(":data-ipconfig")
include(":domain")
include(":infra-network-connectivity")
include(":ui")
