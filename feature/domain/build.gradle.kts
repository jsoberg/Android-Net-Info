plugins {
    id("local.kotlin.library.module")
    id("java-test-fixtures")
}

dependencies {
    api(projects.base.types)

    implementation(libs.kotlin.coroutines)

    testImplementation(libs.bundles.unitTest)

    testFixturesApi(testFixtures(projects.base.types))
}