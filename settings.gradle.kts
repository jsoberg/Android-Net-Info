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
include(":data-network-connectivity")
include(":domain")
include(":infra-viewmodel-ext")
include(":ui")
