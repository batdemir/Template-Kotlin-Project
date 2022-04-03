// /*
// * Designed and developed by 2021 Batuhan Demir
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AppConfig.compileSdk
    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        testInstrumentationRunner = AppConfig.defaultAndroidTestInstrumentation
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
        dataBinding = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildTypes.all {
        this.buildConfigField(
            "String",
            "VERSION_NAME",
            "\"${AppConfig.versionName}\""
        )
        this.buildConfigField(
            "Integer",
            "VERSION_CODE",
            AppConfig.versionCode.toString()
        )
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(Libraries.appcompat.path)
    implementation(Libraries.hiltAndroid.path)
    kapt(Libraries.hiltAndroidCompiler.path)
    implementation(Libraries.kotlinxCoroutinesAndroid.path)
    implementation(Libraries.lifecycleLivedata.path)
    implementation(Libraries.lingver.path)
    implementation(Libraries.material.path)
    implementation(Libraries.pagingGuava.path)
    implementation(Libraries.pagingRuntime.path)
    implementation(Libraries.preference.path)
    implementation(Libraries.retrofit.path)
    implementation(Libraries.retrofitOkhttp.path)
    implementation(Libraries.room.path)
    implementation(Libraries.roomRuntime.path)
    kapt(Libraries.roomCompiler.path)
    implementation(Libraries.startupRuntime.path)
    implementation(Libraries.timber.path)
    testImplementation(Libraries.junit.path)
    androidTestImplementation(Libraries.extJunit.path)
    androidTestImplementation(Libraries.espressoCore.path)
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}
