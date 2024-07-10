// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath(libs.gradle)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.jacoco.gradle)
        classpath(libs.ktlint.gradle)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}