// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath(libs.gradle)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.jacoco.gradle)
        classpath(libs.ktlint.gradle)
        classpath(libs.google.services)
        classpath(libs.firebase.crashlytics.gradle)
    }
}
plugins {
    id("com.google.devtools.ksp") version "1.9.25-1.0.20"
    kotlin("plugin.serialization") version "1.9.25"
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "com.google.devtools.ksp")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
}