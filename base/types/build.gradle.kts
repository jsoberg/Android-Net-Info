plugins {
    alias(libs.plugins.local.kotlin.library)
    alias(libs.plugins.java.testFixtures)
}

dependencies {
    testImplementation(libs.bundles.unitTest)
    testRuntimeOnly(libs.test.junitJupiterLauncher)
}