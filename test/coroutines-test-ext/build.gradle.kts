plugins {
    alias(libs.plugins.local.kotlin.library)
}

dependencies {
    implementation(platform(libs.test.junitJupiter.bom))
    implementation(libs.test.junitJupiter)
    implementation(libs.test.kotlin.coroutines)
}