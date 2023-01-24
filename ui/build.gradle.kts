plugins {
    id(Plugins.Android.Library)
    id(Plugins.Local.AndroidModule)
}

dependencies {
    implementation(libs.compose.material3)
    implementation(libs.compose.foundation)
    implementation(libs.compose.tooling)
    implementation(libs.compose.ui)
}

android {
    namespace = "com.soberg.netinfo.android.ui"

    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
}