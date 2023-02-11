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

    implementation(libs.kotlin.stdlib)

    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)

    testImplementation(libs.bundles.robolectricTest)
    testImplementation(libs.test.kotlin.coroutines)
    testImplementation(libs.test.turbine)
}