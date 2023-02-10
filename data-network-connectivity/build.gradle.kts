plugins {
    id("com.android.library")
    id("android.module")
    kotlin("kapt")
}

android {
    namespace = "com.soberg.netinfo.android.data.netconnectivity"
}

dependencies {
    api(projects.domain)
    implementation(projects.baseAnnotations)

    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)

    testImplementation(libs.bundles.unitTest)
    testImplementation(libs.test.kotlin.coroutines)
    testImplementation(libs.test.robolectric)
    testImplementation(libs.test.turbine)
}