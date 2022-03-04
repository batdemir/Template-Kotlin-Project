@file:Suppress("SpellCheckingInspection")

import models.Dependency

object AppDependencies {
    val appLibraries = arrayListOf<Dependency>().apply {
        add(Libraries.appcompat)
        add(Libraries.archCoreRuntime)
        add(Libraries.constraintLayout)
        add(Libraries.core)
        add(Libraries.fragment)
        add(Libraries.legacySupport)
        add(Libraries.lifecycleCommon)
        add(Libraries.lifecycleExtensions)
        add(Libraries.lifecycleLivedata)
        add(Libraries.lifecycleViewModel)
        add(Libraries.lifecycleViewModelSavedState)
        add(Libraries.multidex)
        add(Libraries.navigationCompose)
        add(Libraries.navigationDynamicFeatures)
        add(Libraries.navigationFragment)
        add(Libraries.navigationUi)
        add(Libraries.pagingGuava)
        add(Libraries.pagingRuntime)
        add(Libraries.preference)
        add(Libraries.room)
        add(Libraries.roomRuntime)
        add(Libraries.startupRuntime)
        add(Libraries.viewpager2)
        add(Libraries.glide)
        add(Libraries.glideOkhttp3Integration)
        add(Libraries.lingver)
        add(Libraries.mpAndroidChart)
        add(Libraries.hiltAndroid)
        add(Libraries.timber)
        add(Libraries.kotlinxCoroutinesCore)
        add(Libraries.kotlinxCoroutinesAndroid)
        add(Libraries.retrofit)
        add(Libraries.retrofitConverterGson)
        add(Libraries.retrofitOkhttp)
        add(Libraries.retrofitLoggingInterceptor)
    }

    val testLibraries = arrayListOf<Dependency>().apply {
        add(Libraries.truth)
        add(Libraries.junit)
    }

    val androidTestLibraries = arrayListOf<Dependency>().apply {
        add(Libraries.archCoreTesting)
        add(Libraries.espressoCore)
        add(Libraries.extJunit)
        add(Libraries.hiltAndroidTesting)
        add(Libraries.truth)
        add(Libraries.kotlinxCoroutinesTest)
    }

    val compilerLibraries = arrayListOf<Dependency>().apply {
        add(Libraries.roomCompiler)
        add(Libraries.glideCompiler)
        add(Libraries.hiltAndroidCompiler)
    }

    val compilerTestLibraries = arrayListOf<Dependency>().apply {
    }

    val compilerAndroidTestLibraries = arrayListOf<Dependency>().apply {
        add(Libraries.hiltAndroidCompiler)
    }

    val debugLibraries = arrayListOf<Dependency>().apply {
        add(Libraries.chuckLibrary)
    }

    val releaseLibraries = arrayListOf<Dependency>().apply {
        add(Libraries.chuckLibraryNoOp)
    }
}