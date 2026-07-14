import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

apply(from = "${project.rootDir}/jacoco/jacoco.gradle")

val localProps = Properties()
val localPropsFile = rootProject.file("local.properties")
if (localPropsFile.exists()) localProps.load(localPropsFile.inputStream())
val aiChatBaseUrl: String = localProps.getProperty("AI_CHAT_BASE_URL") ?: ""
val aiChatEndpointPath: String = localProps.getProperty("AI_CHAT_ENDPOINT_PATH") ?: "chat"
val geminiApiKey: String = localProps.getProperty("GEMINI_API_KEY") ?: ""

android {
    namespace = "com.cyd.data.aichat"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
        buildConfigField("String", "AI_CHAT_BASE_URL", "\"$aiChatBaseUrl\"")
        buildConfigField("String", "AI_CHAT_ENDPOINT_PATH", "\"$aiChatEndpointPath\"")
    }

    buildTypes {
        getByName("debug") {
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
            buildConfigField("String", "GEMINI_API_KEY", "\"$geminiApiKey\"")
        }
        getByName("release") {
            buildConfigField("String", "GEMINI_API_KEY", "\"\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:base"))
    implementation(project(":domain"))

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.gson)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.google.generativeai)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.ktor.client.mock)
}