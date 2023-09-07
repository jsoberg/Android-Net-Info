plugins {
    id("com.android.library")
    id("local.android.module")
    id("local.android.compose")
    kotlin("kapt")
}

dependencies {
    implementation(projects.feature.domain)
    implementation(projects.feature.resources.drawables)
    implementation(projects.feature.resources.strings)
    implementation(projects.infra.androidServices)
    implementation(projects.infra.composeExt)
    implementation(projects.infra.viewmodelExt)

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
}
