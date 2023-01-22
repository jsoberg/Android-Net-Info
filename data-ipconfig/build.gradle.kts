// Temporary fix for https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(Plugins.Local.KotlinJvmLibraryModule)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    api(projects.domain)
    implementation(projects.baseAnnotations)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.serializationJson)
    implementation(libs.bundles.ktor.client)

    testImplementation(libs.bundles.unitTest)
    testImplementation(libs.test.kotlin.coroutines)
    testImplementation(libs.test.ktor.mock)

    testImplementation(testFixtures(projects.domain))
}