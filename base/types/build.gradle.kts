plugins {
    id("local.kotlin.library.module")
    id("java-test-fixtures")
}

dependencies {
    testImplementation(libs.bundles.unitTest)
}