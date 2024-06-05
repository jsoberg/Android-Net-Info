plugins {
    alias(libs.plugins.local.kotlin.library)
}

dependencies {
    implementation(libs.test.junitJupiter)
    implementation(libs.test.kotlin.coroutines)
}