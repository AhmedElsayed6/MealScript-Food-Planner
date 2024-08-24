plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.example.mealscript"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mealscript"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.core.animation)
    implementation(libs.legacy.support.v4)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    implementation("com.google.android.material:material:1.6.1")
    implementation("com.github.yuyakaido:CardStackView:v2.3.4")
    implementation("com.airbnb.android:lottie:5.0.3")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("io.reactivex.rxjava3:rxjava:3.1.6")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("com.google.firebase:firebase-firestore:24.7.0")
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation("com.google.android.gms:play-services-auth:21.2.0")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.11.0")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("androidx.room:room-rxjava3:2.6.1")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}