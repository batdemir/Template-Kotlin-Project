@file:Suppress("SpellCheckingInspection")

object AppDependencies {
    val appLibraries = arrayListOf<String>().apply {
        add("androidx.appcompat:appcompat:${Versions.androidxAppCompatVersion}")
        add("androidx.core:core-ktx:${Versions.androidxCoreVersion}")
        add("androidx.lifecycle:lifecycle-common-java8:${Versions.androidxLifeCycleCommonVersion}")
        add("androidx.lifecycle:lifecycle-extensions:${Versions.androidxLifeCycleExtensionsVersion}")
        add("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidxLifeCycleLiveDataVersion}")
        add("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidxLifeCycleViewModelVersion}")
        add("androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.androidxLifeCycleStateVersion}")
        add("androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayoutVersion}")
        add("androidx.legacy:legacy-support-v4:${Versions.androidxLegacySupportVersion}")
        add("androidx.fragment:fragment-ktx:${Versions.androidxFragmentVersion}")
        add("androidx.room:room-ktx:${Versions.androidxRoomVersion}")
        add("androidx.room:room-runtime:${Versions.androidxRoomVersion}")
        add("androidx.navigation:navigation-compose:${Versions.androidxNavigationVersion}")
        add("androidx.navigation:navigation-dynamic-features-fragment:${Versions.androidxNavigationVersion}")
        add("androidx.navigation:navigation-fragment-ktx:${Versions.androidxNavigationVersion}")
        add("androidx.navigation:navigation-ui-ktx:${Versions.androidxNavigationVersion}")
        add("androidx.paging:paging-guava:${Versions.androidxPagingVersion}")
        add("androidx.paging:paging-runtime-ktx:${Versions.androidxPagingVersion}")
        add("androidx.preference:preference-ktx:${Versions.androidxPreferenceVersion}")
        add("androidx.viewpager2:viewpager2:${Versions.androidxViewPagerVersion}")
        add("androidx.startup:startup-runtime:${Versions.androidxStartUpVersion}")
        add("com.google.android.material:material:${Versions.googleMaterialVersion}")
        add("com.google.dagger:hilt-android:${Versions.googleHiltVersion}")
        add("com.google.firebase:firebase-analytics-ktx:${Versions.googleFirebaseAnalyticsVersion}")
        add("com.google.firebase:firebase-bom:${Versions.googleFirebaseBomVersion}")
        add("com.google.firebase:firebase-config-ktx:${Versions.googleFirebaseConfigVersion}")
        add("com.google.firebase:firebase-firestore-ktx:${Versions.googleFirebaseFireStoreVersion}")
        add("com.google.firebase:firebase-perf:${Versions.googleFirebasePerfVersion}")
        add("com.github.bumptech.glide:glide:${Versions.githubGlideVersion}")
        add("com.github.bumptech.glide:okhttp3-integration:${Versions.githubGlideVersion}")
        add("com.github.PhilJay:MPAndroidChart:${Versions.githubMPAndroidChartVersion}")
        add("com.github.YarikSOffice:lingver:${Versions.lingverVersion}")
        add("com.jakewharton.timber:timber:${Versions.timberVersion}")
        add("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.jetBrainsCoroutinesVersion}")
        add("com.squareup.retrofit2:retrofit:${Versions.squareupRetrofitVersion}")
        add("com.squareup.retrofit2:converter-gson:${Versions.squareupRetrofitVersion}")
        add("com.squareup.okhttp3:okhttp:${Versions.squareupOkHttpVersion}")
        add("com.squareup.okhttp3:logging-interceptor:${Versions.squareupOkHttpVersion}")
    }

    val compilerLibraries = arrayListOf<String>().apply {
        add("androidx.room:room-compiler:${Versions.androidxRoomVersion}")
        add("com.github.bumptech.glide:compiler:${Versions.githubGlideVersion}")
        add("com.google.dagger:hilt-android-compiler:${Versions.googleHiltVersion}")
    }

    val debugLibraries = arrayListOf<String>().apply {
        add("com.readystatesoftware.chuck:library:${Versions.chuckVersion}")
    }

    val releaseLibraries = arrayListOf<String>().apply {
        add("com.readystatesoftware.chuck:library-no-op:${Versions.chuckVersion}")
    }

    val testLibraries = arrayListOf<String>().apply {
        add("junit:junit:${Versions.testJunitVersion}")
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add("androidx.test.ext:junit:${Versions.testAndroidxTestJunit}")
    }
}