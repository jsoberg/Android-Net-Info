plugins {
    id("java-library")
    kotlin("jvm")
}

dependencies {
    implementation(libs.kotlin.stdlib)

    testImplementation(libs.bundles.unitTest)
}