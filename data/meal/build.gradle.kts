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
    namespace = "com.cyd.data.meal"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    implementation(project(":data:network"))
    implementation(project(":data:db"))
    implementation(project(":core:base"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}