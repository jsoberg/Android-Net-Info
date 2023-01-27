// Temporary fix for https://youtrack.jetbrains.com/issue/KTIJ-19369
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(Plugins.Android.Application)
    id(Plugins.Local.AndroidModule)
    alias(libs.plugins.hilt)
    id(Plugins.Kotlin.Kapt)
}

android {
    namespace = "com.soberg.netinfo.android.app"

    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()

    defaultConfig {
        applicationId = "com.soberg.netinfo.android.app"
        versionCode = 1
        versionName = "0.1"
    }
}

dependencies {
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.coroutines)

    implementation(libs.google.material)

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
