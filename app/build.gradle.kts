

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android") version "1.8.22"
}

android {
    namespace = "com.example.foodorderapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.foodorderapp"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}



dependencies {
    // AppCompat
    implementation("androidx.appcompat:appcompat:1.6.1")
    // Material Components
    implementation("com.google.android.material:material:1.9.0")
    // ConstraintLayout
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    // ViewPager2
    implementation("androidx.viewpager2:viewpager2:1.1.0")
    implementation(libs.activity)
    // Kotlin stdlib

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}


