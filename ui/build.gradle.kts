plugins {
    id("com.android.library")
    id("android.module")
    kotlin("kapt")
}

dependencies {
    implementation(projects.domain)
    implementation(projects.infraViewmodelExt)

    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    testImplementation(composeBom)

    implementation(libs.bundles.compose.core)
    implementation(libs.androidX.lifecycle.viewModel)

    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)

    debugImplementation(libs.test.compose.manifest)
    testImplementation(libs.bundles.robolectricTest)
    testImplementation(libs.test.compose.junit)
}

android {
    namespace = "com.soberg.netinfo.android.ui"

    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
}
