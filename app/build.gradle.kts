plugins {
    alias(libs.plugins.local.android.app)
    alias(libs.plugins.local.android.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.soberg.netinfo.android.app"

    defaultConfig {
        applicationId = "com.soberg.netinfo.android.app"
        versionCode = 1
        versionName = "0.1"
    }
}

dependencies {
    implementation(projects.base.annotations)
    implementation(projects.base.logging)
    implementation(projects.feature.data.ipconfig)
    implementation(projects.feature.data.networkConnectivity)
    implementation(projects.feature.domain)
    implementation(projects.feature.ui)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.coroutines)

    implementation(libs.google.material)

    implementation(libs.androidX.activityCompose)
    implementation(libs.androidX.navigationCompose)
    implementation(libs.compose.runtime)
    implementation(libs.bundles.compose.core)

    ksp(libs.hilt.compiler)
    ksp(libs.hilt.android.compiler)
    implementation(libs.dagger)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigationCompose)
}