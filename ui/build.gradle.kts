plugins {
    id("com.android.library")
    id("android.module")
    kotlin("kapt")
}

dependencies {
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.bundles.compose.core)
    implementation(libs.androidX.lifecycle.viewModel)

    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)
}

android {
    namespace = "com.soberg.netinfo.android.ui"

    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
}
