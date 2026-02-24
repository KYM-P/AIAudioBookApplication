plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.design"
    compileSdk = 36

    defaultConfig {
        minSdk = 28

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // compose bom
    implementation(platform(libs.androidx.compose.bom))

    // compose implementation
    listOf(
        libs.androidx.activity.compose,
        libs.androidx.compose.material3,
        libs.androidx.compose.material3.adaptive,
        libs.androidx.compose.ui,
        libs.androidx.compose.foundation,
        libs.androidx.constraintlayout.compose,
        libs.androidx.lifecycle.compose
    ).forEach { implementation(it) }

    // compose debugImplementation
    listOf(
        libs.androidx.compose.ui.test.manifest,
        libs.androidx.compose.ui.tooling,
    ).forEach { debugImplementation(it) }
}