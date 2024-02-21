plugins {
    id("local.kotlin.library.module")
}

dependencies {
    implementation(libs.test.junitJupiter)
    implementation(libs.test.kotlin.coroutines)
}