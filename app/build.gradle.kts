plugins {
    id("com.android.application")
    id("kotlin-android")
    alias(libs.plugins.hilt)
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    alias(libs.plugins.kotlin.compose)
}
apply(from = "${project.rootDir}/jacoco/jacoco.gradle")

android {
    compileSdk = 36

    defaultConfig {
        applicationId = "com.cyd"
        minSdk = 24
        targetSdk = 36
        versionCode = 10
        versionName = "0.10"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            isDebuggable = false
        }
        create("benchmark") {
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            proguardFiles("benchmark-rules.pro")
            isDebuggable = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += listOf("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    namespace = "com.cyd"
    androidResources {
        additionalParameters += listOf("--warn-manifest-validation")
    }
}

dependencies {
    implementation(project(":data:categories"))
    implementation(project(":data:ingredients"))
    implementation(project(":data:meal"))

    implementation(project(":data:network"))
    implementation(project(":core:ui"))
    implementation(project(":core:base"))

    implementation(project(":feature:categories"))
    implementation(project(":feature:randommeal"))
    implementation(project(":feature:categorymeals"))
    implementation(project(":feature:mealdetails"))
    implementation(project(":feature:search"))

    implementation(libs.androidx.ktx)
    implementation(libs.material)
    implementation(libs.airbnb.lottie.compose)

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material3.window.size)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material.icons.core)

    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    implementation(libs.coil.compose)
    implementation(libs.coil.gif)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.profileinstaller)
    implementation(libs.kotlinx.serialization.json)

    debugImplementation(libs.androidx.ui.tooling)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)

    debugImplementation(libs.androidx.customview)
    debugImplementation(libs.androidx.customview.poolingcontainer)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)
}

allprojects {
    apply(plugin = "jacoco")
    gradle.projectsEvaluated {
        tasks.withType<JavaCompile>().configureEach {
            options.compilerArgs.add("-Xlint:deprecation")
        }
    }
}
