plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}
apply(from = "${project.rootDir}/jacoco/jacoco.gradle")

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cyd"
        minSdk = 24
        targetSdk = 34
        versionCode = 4
        versionName = "0.4"

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
                "proguard-rules.pro"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
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
    implementation(project(":data:meal"))

    implementation(project(":data:network"))
    implementation(project(":core:ui"))
    implementation(project(":core:base"))

    implementation(project(":feature:categories"))
    implementation(project(":feature:random_meal"))
    implementation(project(":feature:category_meals"))
    implementation(project(":feature:meal_details"))

    implementation(libs.androidx.ktx)
    implementation(libs.material)
    implementation(libs.airbnb.lottie.compose)

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material3.window.size)

    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.compiler)

    implementation(libs.coil.compose)
    implementation(libs.coil.gif)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.profileinstaller)

    debugImplementation(libs.androidx.ui.tooling)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)

    debugImplementation(libs.androidx.customview)
    debugImplementation(libs.androidx.customview.poolingcontainer)

    implementation(platform(libs.firebase.bom))
}

allprojects {
    apply(plugin = "jacoco")
    gradle.projectsEvaluated {
        tasks.withType<JavaCompile>().configureEach {
            options.compilerArgs.add("-Xlint:deprecation")
        }
    }
}