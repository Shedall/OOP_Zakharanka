// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0-beta03" apply false
}
buildscript {
    repositories {
        // Make sure that you have the following two repositories
        google()  // Google's Maven repository

        mavenCentral()  // Maven Central repository
    }
    dependencies {
        // Add the dependency for the Google services Gradle plugin
        classpath("com.google.gms:google-services:4.3.15")

    }
}