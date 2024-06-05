plugins {
    alias(libs.plugins.local.kotlin.library)
    alias(libs.plugins.java.testFixtures)
}

dependencies {
    api(projects.base.types)

    implementation(libs.kotlin.coroutines)

    testImplementation(libs.bundles.unitTest)

    testFixturesApi(testFixtures(projects.base.types))
}