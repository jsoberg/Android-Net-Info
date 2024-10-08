plugins {
    alias(libs.plugins.local.android.library)
    alias(libs.plugins.local.android.compose)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(projects.feature.domain)
    implementation(projects.feature.resources.drawables)
    implementation(projects.feature.resources.lottie)
    implementation(projects.feature.resources.strings)
    implementation(projects.infra.androidServices)
    implementation(projects.infra.composeExt)
    implementation(projects.infra.viewmodelExt)

    implementation(libs.bundles.compose.core)
    implementation(libs.lottie.compose)

    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)

    debugImplementation(libs.test.compose.manifest)
    testImplementation(libs.bundles.robolectricTest)
    testImplementation(libs.test.compose.junit)
}

android {
    namespace = "com.soberg.netinfo.android.ui"
}
