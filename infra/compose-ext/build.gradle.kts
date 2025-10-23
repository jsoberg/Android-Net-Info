plugins {
    alias(libs.plugins.local.android.library)
    alias(libs.plugins.local.android.compose)
}

android {
    namespace = "com.soberg.netinfo.android.infra.compose.ext"
}

dependencies {
    implementation(libs.kotlin.stdlib)

    implementation(libs.compose.ui)
    implementation(libs.androidX.lifecycle.composeRuntime)
}