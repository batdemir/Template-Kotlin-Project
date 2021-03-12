///*
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
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    defaultConfig {
        applicationId(AppConfig.applicationId)
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode(AppConfig.versionCode)
        versionName(AppConfig.versionName)
        testInstrumentationRunner(AppConfig.androidTestInstrumentation)
    }
    buildFeatures {
        dataBinding = true
    }
    buildTypes {
        getByName(BuildType.DEBUG.value) {
            this.buildConfigField(
                "String",
                "GITHUB_API",
                properties["TEST_GITHUB_API"].toString()
            )
            this.buildConfigField(
                "String",
                "STACK_OVER_FLOW_API",
                properties["TEST_STACK_OVER_FLOW_API"].toString()
            )
            this.resValue("string", "app_name", getAppName(BuildType.DEBUG))
        }
        getByName(BuildType.RELEASE.value) {
            this.buildConfigField(
                "String",
                "GITHUB_API",
                properties["PROD_GITHUB_API"].toString()
            )
            this.buildConfigField(
                "String",
                "STACK_OVER_FLOW_API",
                properties["PROD_STACK_OVER_FLOW_API"].toString()
            )
            this.resValue("string", "app_name", getAppName(BuildType.RELEASE))
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
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    kapt(AppDependencies.compilerLibraries)
    implementation(AppDependencies.appLibraries)
    debugImplementation(AppDependencies.debugLibraries)
    releaseImplementation(AppDependencies.releaseLibraries)
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)
}

enum class BuildType(val value: String) {
    DEBUG("debug"),
    RELEASE("release")
}

fun getAppName(buildType: BuildType): String {
    val appName = "batdemir"
    return when (buildType) {
        BuildType.DEBUG -> "$appName - Debug"
        BuildType.RELEASE -> appName
    }
}