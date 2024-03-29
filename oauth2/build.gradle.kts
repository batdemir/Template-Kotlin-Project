import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

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
        all {
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
            this.buildConfigField(
                "String",
                "AUTH_BASE_URL",
                "\"${gradleLocalProperties(rootDir).getProperty("auth_base_url")}\""
            )
            this.buildConfigField(
                "String",
                "TOKEN_BASE_URL",
                "\"${gradleLocalProperties(rootDir).getProperty("token_base_url")}\""
            )
            this.buildConfigField(
                "String",
                "CLIENT_ID",
                "\"${gradleLocalProperties(rootDir).getProperty("client_id")}\""
            )
            this.buildConfigField(
                "String",
                "SECRET_KEY",
                "\"${gradleLocalProperties(rootDir).getProperty("secret_key")}\""
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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
    implementation(project(":core"))
    implementation(Libraries.appcompat.path)
    implementation(Libraries.hiltAndroid.path)
    kapt(Libraries.hiltAndroidCompiler.path)
    implementation(Libraries.lifecycleExtensions.path)
    implementation(Libraries.lifecycleLivedata.path)
    implementation(Libraries.lifecycleRuntime.path)
    implementation(Libraries.lifecycleViewModel.path)
    implementation(Libraries.lifecycleViewModelSavedState.path)
    implementation(Libraries.retrofit.path)
    implementation(Libraries.retrofitConverterGson.path)
    implementation(Libraries.retrofitOkhttp.path)
    implementation(Libraries.retrofitLoggingInterceptor.path)
    implementation(Libraries.timber.path)
    debugImplementation(AppDependencies.debugLibraries)
    releaseImplementation(AppDependencies.releaseLibraries)
    testImplementation(Libraries.junit.path)
    androidTestImplementation(Libraries.extJunit.path)
    androidTestImplementation(Libraries.espressoCore.path)
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}
