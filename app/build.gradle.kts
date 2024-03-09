plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.mygithubuser"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mygithubuser"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_KEY", "\"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\"")
        buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures{
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Navigation Implementation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")

    // Retrofit, Glide & Circle Image Implementation
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // View Model Implementation
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.activity:activity-ktx:1.8.0")

    // Live Data Implementation
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // View Pager Implementation
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    // Data Store Implementation
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    // Room Implementation
    implementation("androidx.room:room-runtime:2.5.2")
    implementation("androidx.test:core-ktx:1.5.0")
    testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.12")
    ksp("androidx.room:room-compiler:2.5.2")

    // Unit Testing Implementation
    testImplementation("org.mockito:mockito-core:4.4.0")
    testImplementation("org.mockito:mockito-inline:4.4.0")

    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
}