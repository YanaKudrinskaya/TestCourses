plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.navigation.safeargs)
    id("kotlin-kapt")
    alias(libs.plugins.google.dagger.hilt.android)
}

android {
    namespace = "com.yanakudrinskaya.main"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core"))
//
//    implementation(project(":features:auth"))
//    implementation(project(":features:home"))
//    implementation(project(":features:favorites"))
//    implementation(project(":features:account"))
//    implementation(project(":features:course"))

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Activity
    implementation(libs.androidx.activity.ktx)

    //Hilt
    implementation(libs.hilt.android)
    kapt (libs.hilt.compiler)
}