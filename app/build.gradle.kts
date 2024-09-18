plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.instaflix"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.instaflix"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"


        testInstrumentationRunner = "com.example.instaflix.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.hilt.android.v2511)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.fragment)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.paging)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    implementation(libs.coil.compose)

    implementation(libs.accompanist.navigation.animation)

    // Unit Testing
    testImplementation(libs.junit)
    testImplementation(libs.mockito.mockito.core)
    testImplementation(libs.kotlin.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test.v152)
    testImplementation(libs.androidx.core.testing.v210 )

    // Mockito with Kotlin
    testImplementation( libs.mockitokotlin2.mockito.kotlin)

    // AndroidX Test
    testImplementation(libs.androidx.junit)
    testImplementation(libs.core)

    // Room Test Helpers
    testImplementation(libs.androidx.room.testing.v250)

    // Android Instrumented Testing
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.junit.v113)
    androidTestImplementation(libs.androidx.espresso.core.v340)

    // Room and ViewModel Instrumented Testing
    androidTestImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.androidx.room.testing)

    // Instrumentation for Coroutines
    androidTestImplementation(libs.kotlinx.coroutines.test)

    // Jetpack Compose Testing
    androidTestImplementation(libs.ui.test.junit4)

    // For instrumented tests.
    androidTestImplementation(libs.hilt.android.testing)
    // ...with Kotlin.
    kaptAndroidTest(libs.hilt.android.compiler)

}

