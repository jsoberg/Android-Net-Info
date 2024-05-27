plugins {
    id("local.android.library")
}

android {
    namespace = "com.soberg.netinfo.android.infra.viewmodel.ext"
}

dependencies {
    implementation(libs.kotlin.stdlib)

    implementation(libs.androidX.lifecycle.viewModel)

    testImplementation(libs.bundles.robolectricTest)
    testImplementation(libs.test.kotlin.coroutines)
    testImplementation(libs.test.turbine)
}