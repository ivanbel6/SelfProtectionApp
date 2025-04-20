plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("androidx.room") version "2.6.0"

}
android {
    namespace = "com.example.selfprotectionapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.app"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11 // Updated to Java 11
        targetCompatibility = JavaVersion.VERSION_11 // Updated to Java 11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
    packagingOptions {
        resources {
            excludes += listOf("META-INF/NOTICE.md", "META-INF/LICENSE.md")
        }
    }


}

dependencies {
    implementation("com.google.android.material:material:1.10.0")
    // Jetpack Compose
    implementation("androidx.compose.ui:ui:1.5.3")
    implementation("androidx.compose.material:material:1.5.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3")

    // Room (kapt)
    implementation("androidx.room:room-runtime:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")
    implementation(libs.androidx.material3.android)
    ksp("androidx.room:room-compiler:2.6.0")

    // Hilt (ksp)
    implementation("com.google.dagger:hilt-android:2.48")
    ksp("com.google.dagger:hilt-compiler:2.48") // Standardized on KSP
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.23")

    implementation("androidx.navigation:navigation-compose:2.7.0")

    implementation("com.vk:android-sdk-core:4.1.0")
    implementation("com.vk:android-sdk-api:4.1.0") // generated models and api methods

    implementation("com.sun.mail:jakarta.mail:2.0.1")
}
