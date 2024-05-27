plugins {
    id("local.android.library")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.soberg.netinfo.android.data.netconnectivity"
}

dependencies {
    api(projects.feature.domain)
    implementation(projects.base.annotations)
    implementation(projects.base.logging)

    implementation(libs.kotlin.stdlib)

    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)

    testImplementation(libs.bundles.robolectricTest)
    testImplementation(libs.test.kotlin.coroutines)
    testImplementation(libs.test.turbine)
}