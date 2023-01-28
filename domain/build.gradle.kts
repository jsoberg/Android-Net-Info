plugins {
    id("kotlin.library.module")
    id("java-test-fixtures")
}

dependencies {
    api(projects.baseTypes)

    testImplementation(libs.bundles.unitTest)

    testFixturesApi(testFixtures(projects.baseTypes))
}