plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}
apply(from = "${project.rootDir}/jacoco/jacoco.gradle")

android {
    buildTypes {
        getByName("debug") {
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
        }
    }
    namespace = "com.cyd.ui"
    compileSdk = 36
    defaultConfig {
        minSdk = 24
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }
}

dependencies {
    implementation(project(":core:base"))

    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)

    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material3.window.size)
    implementation(libs.androidx.animation)
    debugImplementation(libs.androidx.customview)
    debugImplementation(libs.androidx.customview.poolingcontainer)

    implementation(libs.coil.compose)
    implementation(libs.coil.gif)
}