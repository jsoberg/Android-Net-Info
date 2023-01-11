plugins {
    id("java-library")
    kotlin("jvm")
}

dependencies {
    api(projects.baseTypes)

    testImplementation(libs.bundles.unitTest)
}