@file:Suppress("SpellCheckingInspection")

object AppDependencies {
    val appLibraries = arrayListOf<String>().apply {
        add("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}")
        add("com.google.android.material:material:${Versions.materialVersion}")
        add("androidx.appcompat:appcompat:${Versions.appCompatVersion}")
        add("androidx.legacy:legacy-support-v4:${Versions.legacySupportVersion}")
        add("androidx.constraintlayout:constraintlayout:${Versions.constraintVersion}")
        add("androidx.core:core-ktx:${Versions.coreVersion}")
        add("androidx.fragment:fragment-ktx:${Versions.fragmentVersion}")
        add("androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}")
        add("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleLiveVersion}")
        add("androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleLiveVersion}")
        add("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}")
        add("androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycleStateVersion}")
        add("androidx.preference:preference-ktx:${Versions.preferenceVersion}")
        add("androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}")
        add("androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}")
        add("androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigationVersion}")
        add("androidx.navigation:navigation-compose:${Versions.navigationCompVersion}")
        add("androidx.viewpager2:viewpager2:${Versions.viewPagerVersion}")
        add("androidx.paging:paging-runtime-ktx:${Versions.pagingVersion}")
        add("androidx.paging:paging-guava:${Versions.pagingVersion}")
        add("androidx.startup:startup-runtime:${Versions.startupVersion}")
        add("androidx.room:room-runtime:${Versions.roomVersion}")
        add("androidx.room:room-ktx:${Versions.roomVersion}")
        add("com.jakewharton.timber:timber:${Versions.timberVersion}")
        add("com.github.PhilJay:MPAndroidChart:${Versions.chartVersion}")
        add("com.github.YarikSOffice:lingver:${Versions.lingverVersion}")
        add("com.github.bumptech.glide:glide:${Versions.glideVersion}")
        add("com.github.bumptech.glide:okhttp3-integration:${Versions.glideVersion}")
        add("com.google.dagger:dagger:${Versions.daggerVersion}")
        add("com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}")
        add("com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}")
        add("com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}")
        add("com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}")
        add("com.google.firebase:firebase-bom:${Versions.fireBomVersion}")
        add("com.google.firebase:firebase-analytics-ktx:${Versions.fireAnalyticsVersion}")
        add("com.google.firebase:firebase-perf:${Versions.firePerfVersion}")
        add("com.google.firebase:firebase-firestore-ktx:${Versions.fireFireStoreVersion}")
        add("com.google.firebase:firebase-config-ktx:${Versions.fireConfigVersion}")
    }

    val compilerLibraries = arrayListOf<String>().apply {
        add("androidx.room:room-compiler:${Versions.roomVersion}")
        add("com.github.bumptech.glide:compiler:${Versions.glideVersion}")
        add("com.google.dagger:dagger-compiler:${Versions.daggerVersion}")
    }

    val debugLibraries = arrayListOf<String>().apply {
        add("com.readystatesoftware.chuck:library:${Versions.chuckVersion}")
    }

    val releaseLibraries = arrayListOf<String>().apply {
        add("com.readystatesoftware.chuck:library-no-op:${Versions.chuckVersion}")
    }

    val testLibraries = arrayListOf<String>().apply {
        add("junit:junit:${Versions.junitVersion}")
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add("androidx.test.ext:junit:${Versions.androidxTestJunit}")
    }
}