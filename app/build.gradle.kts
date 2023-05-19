plugins {
    id("com.android.application")
    id("android.compose")
    id("android.module")
    kotlin("kapt")
    alias(libs.plugins.hilt)
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
    implementation(projects.feature.data.ipconfig)
    implementation(projects.feature.data.networkConnectivity)
    implementation(projects.feature.domain)
    implementation(projects.feature.ui)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.coroutines)

    implementation(libs.ktor.client.core)

    implementation(libs.google.material)
    implementation(libs.google.accompanist.systemUiController)

    implementation(libs.androidX.activityCompose)
    implementation(libs.androidX.navigationCompose)
    implementation(libs.compose.runtime)
    implementation(libs.bundles.compose.core)

    kapt(libs.hilt.compiler)
    kapt(libs.hilt.android.compiler)
    implementation(libs.dagger)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigationCompose)
}

kapt.correctErrorTypes = true
