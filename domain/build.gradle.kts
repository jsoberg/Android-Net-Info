plugins {
    id("kotlin.library.module")
    id("java-test-fixtures")
}

dependencies {
    api(projects.baseTypes)

    implementation(libs.kotlin.coroutines)

    testImplementation(libs.bundles.unitTest)

    testFixturesApi(testFixtures(projects.baseTypes))
}