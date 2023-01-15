plugins {
    id("java-library")
    id("java-test-fixtures")
    kotlin("jvm")
}

dependencies {
    api(projects.baseTypes)

    testImplementation(libs.bundles.unitTest)

    testFixturesApi(testFixtures(projects.baseTypes))
}