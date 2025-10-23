plugins {
    alias(libs.plugins.local.kotlin.library)
    alias(libs.plugins.local.junit.testable)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    api(projects.feature.domain)
    implementation(projects.base.annotations)
    implementation(projects.base.logging)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.serializationJson)
    implementation(libs.javax.inject)
    implementation(libs.bundles.ktor.client)

    testImplementation(libs.bundles.unitTest)
    testImplementation(libs.test.kotlin.coroutines)
    testImplementation(libs.test.ktor.mock)

    testImplementation(testFixtures(projects.feature.domain))
}