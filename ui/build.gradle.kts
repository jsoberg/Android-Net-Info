plugins {
    id("com.android.library")
    id("android.module")
}

dependencies {
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.bundles.compose.core)
}

android {
    namespace = "com.soberg.netinfo.android.ui"

    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
}
