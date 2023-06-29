plugins {
    id("com.android.library")
    id("android.compose")
    id("android.module")
}

android {
    namespace = "com.soberg.netinfo.android.infra.compose.ext"
}

dependencies {
    implementation(libs.kotlin.stdlib)

    implementation(libs.compose.ui)
    implementation(libs.androidX.lifecycle.composeRuntime)

    testImplementation(libs.bundles.robolectricTest)
    testImplementation(libs.test.kotlin.coroutines)
    testImplementation(libs.test.turbine)
}