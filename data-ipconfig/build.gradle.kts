plugins {
    id("kotlin.library.module")
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    api(projects.domain)
    implementation(projects.baseAnnotations)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.serializationJson)
    implementation(libs.bundles.ktor.client)

    implementation(libs.dagger)

    testImplementation(libs.bundles.unitTest)
    testImplementation(libs.test.kotlin.coroutines)
    testImplementation(libs.test.ktor.mock)

    testImplementation(testFixtures(projects.domain))
}