plugins {
    alias(libs.plugins.local.android.library)
}

android {
    namespace = "com.soberg.netinfo.android.infra.android.services"
}

dependencies {
    implementation(libs.hilt.android)
    implementation(libs.kotlin.stdlib)

    implementation(projects.base.types)

    testImplementation(libs.bundles.robolectricTest)
    testImplementation(libs.test.kotlin.coroutines)
    testImplementation(libs.test.turbine)
}