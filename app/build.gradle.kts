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
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

val buildType: BuildType = BuildType.RELEASE
val apiTypeName: String = "String"
val githubApi: String = "GITHUB_API"
val stackOverFlowApi: String = "STACK_OVER_FLOW_API"

android {
    compileSdk = AppConfig.compileSdk
    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }
    buildFeatures {
        dataBinding = true
    }
    buildTypes.all {
        when (buildType) {
            BuildType.DEBUG -> {
                this.buildConfigField(
                    apiTypeName,
                    githubApi,
                    properties["TEST_GITHUB_API"].toString()
                )
                this.buildConfigField(
                    apiTypeName,
                    stackOverFlowApi,
                    properties["TEST_STACK_OVER_FLOW_API"].toString()
                )
                this.resValue(
                    "string",
                    "app_name",
                    getAppName(buildType)
                )
            }
            BuildType.RELEASE -> {
                this.buildConfigField(
                    apiTypeName,
                    githubApi,
                    properties["PROD_GITHUB_API"].toString()
                )
                this.buildConfigField(
                    apiTypeName,
                    stackOverFlowApi,
                    properties["PROD_STACK_OVER_FLOW_API"].toString()
                )
                this.resValue(
                    "string",
                    "app_name",
                    getAppName(buildType)
                )
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
}