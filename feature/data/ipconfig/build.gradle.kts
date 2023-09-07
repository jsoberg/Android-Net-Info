plugins {
    id("local.kotlin.library.module")
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    api(projects.feature.domain)
    implementation(projects.base.annotations)
    implementation(projects.base.logging)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.serializationJson)
    implementation(libs.bundles.ktor.client)

    implementation(libs.dagger)

    testImplementation(libs.bundles.unitTest)
    testImplementation(libs.test.kotlin.coroutines)
    testImplementation(libs.test.ktor.mock)

    testImplementation(testFixtures(projects.feature.domain))
}