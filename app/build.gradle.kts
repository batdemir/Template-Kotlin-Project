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
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = AppConfig.compileSdk
    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        multiDexEnabled = true
    }
    buildFeatures {
        dataBinding = true
    }
    // signingConfigs {
    //     getByName("debug") {
    //         storeFile = rootProject.file(gradleLocalProperties(rootDir).getProperty("keystore_path"))
    //         storePassword = gradleLocalProperties(rootDir).getProperty("keystore_password")
    //         keyAlias = gradleLocalProperties(rootDir).getProperty("key_alias")
    //         keyPassword = gradleLocalProperties(rootDir).getProperty("key_alias_password")
    //     }
    //     create("release") {
    //         storeFile = rootProject.file(gradleLocalProperties(rootDir).getProperty("keystore_path"))
    //         storePassword = gradleLocalProperties(rootDir).getProperty("keystore_password")
    //         keyAlias = gradleLocalProperties(rootDir).getProperty("key_alias")
    //         keyPassword = gradleLocalProperties(rootDir).getProperty("key_alias_password")
    //     }
    // }
    buildTypes {
        all {
            this.buildConfigField(
                "String",
                "GITHUB_API",
                "\"${gradleLocalProperties(rootDir).getProperty("github_api")}\""
            )
            this.buildConfigField(
                "String",
                "STACK_OVER_FLOW_API",
                "\"${gradleLocalProperties(rootDir).getProperty("stack_over_flow_api")}\""
            )
        }
        getByName("debug") {
            this.resValue(
                "string",
                "app_name",
                "batdemir - Debug"
            )
            // signingConfig = signingConfigs.getByName("debug")
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            this.resValue(
                "string",
                "app_name",
                "batdemir"
            )
            // signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(
        fileTree(
            mapOf(
                "dir" to "libs",
                "include" to listOf("*.jar")
            )
        )
    )
    implementation(project(":core"))
    implementation(AppDependencies.appLibraries)
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)
    kapt(AppDependencies.compilerLibraries)
    kaptTest(AppDependencies.compilerTestLibraries)
    kaptAndroidTest(AppDependencies.compilerAndroidTestLibraries)
    debugImplementation(AppDependencies.debugLibraries)
    releaseImplementation(AppDependencies.releaseLibraries)
}

kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.RequiresOptIn")
}
