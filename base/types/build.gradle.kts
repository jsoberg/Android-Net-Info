plugins {
    alias(libs.plugins.local.kotlin.library)
    alias(libs.plugins.local.junit.testable)
    alias(libs.plugins.java.testFixtures)
}

dependencies {
    testImplementation(libs.bundles.unitTest)
}