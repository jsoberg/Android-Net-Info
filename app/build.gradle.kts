import com.soberg.gradle.plugin.ext.koverImplementation

plugins {
    id("com.android.application")
    id("local.android.module")
    id("local.android.compose")
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
    koverImplementation(projects.base.annotations)
    koverImplementation(projects.base.logging)
    koverImplementation(projects.base.types)
    koverImplementation(projects.feature.data.ipconfig)
    koverImplementation(projects.feature.data.networkConnectivity)
    koverImplementation(projects.feature.domain)
    koverImplementation(projects.feature.ui)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.coroutines)

    implementation(libs.ktor.client.core)

    implementation(libs.google.material)
    implementation(libs.google.accompanist.systemUiController)

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

koverReport {
    filters {
        excludes {
            annotatedBy("*Generated*", "*Preview*")
        }
    }

    androidReports("debug") {
        html {
            onCheck = false
            setReportDir(layout.buildDirectory.dir("reports/kover/html"))
        }
    }
}
