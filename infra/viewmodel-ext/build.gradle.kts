plugins {
    alias(libs.plugins.local.android.library)
}

android {
    namespace = "com.soberg.netinfo.android.infra.viewmodel.ext"
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidX.lifecycle.viewModel)
}