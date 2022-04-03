import models.Dependency

object Libraries {
    val appcompat = Dependency("androidx.appcompat:appcompat:${Versions.androidxAppCompatVersion}")
    val archCoreRuntime = Dependency("androidx.arch.core:core-runtime:${Versions.androidxArchCoreVersion}")
    val archCoreTesting = Dependency("androidx.arch.core:core-testing:${Versions.androidxArchCoreVersion}")
    val constraintLayout = Dependency("androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayoutVersion}")
    val core = Dependency("androidx.core:core-ktx:${Versions.androidxCoreVersion}")
    val fragment = Dependency("androidx.fragment:fragment-ktx:${Versions.androidxFragmentVersion}")
    val hiltWork = Dependency("androidx.hilt:hilt-work:${Versions.androidxHiltWork}")
    val hiltWorkCompiler = Dependency("androidx.hilt:hilt-compiler:${Versions.androidxHiltWork}")
    val legacySupport = Dependency("androidx.legacy:legacy-support-v4:${Versions.androidxLegacySupportVersion}")
    val lifecycleExtensions = Dependency("androidx.lifecycle:lifecycle-extensions:${Versions.androidxLifeCycleExtensionsVersion}")
    val lifecycleRuntime = Dependency("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifeCycleRuntimeVersion}")
    val lifecycleLivedata = Dependency("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidxLifeCycleLiveDataVersion}")
    val lifecycleViewModel = Dependency("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidxLifeCycleViewModelVersion}")
    val lifecycleViewModelSavedState =
        Dependency("androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.androidxLifeCycleViewModelStateVersion}")
    val multidex = Dependency("androidx.multidex:multidex:${Versions.androidxMultidex}")
    val navigationDynamicFeatures = Dependency("androidx.navigation:navigation-dynamic-features-fragment:${Versions.androidxNavigationVersion}")
    val navigationFragment = Dependency("androidx.navigation:navigation-fragment-ktx:${Versions.androidxNavigationVersion}")
    val navigationUi = Dependency("androidx.navigation:navigation-ui-ktx:${Versions.androidxNavigationVersion}")
    val pagingGuava = Dependency("androidx.paging:paging-guava:${Versions.androidxPagingVersion}")
    val pagingRuntime = Dependency("androidx.paging:paging-runtime-ktx:${Versions.androidxPagingVersion}")
    val preference = Dependency("androidx.preference:preference-ktx:${Versions.androidxPreferenceVersion}")
    val room = Dependency("androidx.room:room-ktx:${Versions.androidxRoomVersion}")
    val roomPaging = Dependency("androidx.room:room-paging:${Versions.androidxRoomVersion}")
    val roomRuntime = Dependency("androidx.room:room-runtime:${Versions.androidxRoomVersion}")
    val roomCompiler = Dependency("androidx.room:room-compiler:${Versions.androidxRoomVersion}")
    val startupRuntime = Dependency("androidx.startup:startup-runtime:${Versions.androidxStartUpVersion}")
    val swipeRefreshLayout = Dependency("androidx.swiperefreshlayout:swiperefreshlayout:${Versions.androidxSwipeRefreshLayoutVersion}")
    val viewpager2 = Dependency("androidx.viewpager2:viewpager2:${Versions.androidxViewPagerVersion}")
    val webKit = Dependency("androidx.webkit:webkit:${Versions.androidxWebKitVersion}")
    val workManagerRuntime = Dependency("androidx.work:work-runtime-ktx:${Versions.androidxWorkManager}")
    val workManagerMultiProcess = Dependency("androidx.work:work-multiprocess:${Versions.androidxWorkManager}")
    val glide = Dependency("com.github.bumptech.glide:glide:${Versions.githubGlideVersion}")
    val glideCompiler = Dependency("com.github.bumptech.glide:compiler:${Versions.githubGlideVersion}")
    val glideOkhttp3Integration = Dependency("com.github.bumptech.glide:okhttp3-integration:${Versions.githubGlideVersion}")
    val lingver = Dependency("com.github.YarikSOffice:lingver:${Versions.githubLingverVersion}")
    val mpAndroidChart = Dependency("com.github.PhilJay:MPAndroidChart:${Versions.githubMPAndroidChartVersion}")
    val material = Dependency("com.google.android.material:material:${Versions.googleMaterialVersion}")
    val hiltAndroid = Dependency("com.google.dagger:hilt-android:${Versions.googleHiltVersion}")
    val hiltAndroidCompiler = Dependency("com.google.dagger:hilt-android-compiler:${Versions.googleHiltVersion}")
    val hiltAndroidTesting = Dependency("com.google.dagger:hilt-android-testing:${Versions.googleHiltVersion}")
    val timber = Dependency("com.jakewharton.timber:timber:${Versions.timberVersion}")
    val kotlinxCoroutinesCore = Dependency("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.jetBrainsCoroutinesVersion}")
    val kotlinxCoroutinesAndroid = Dependency("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.jetBrainsCoroutinesVersion}")
    val kotlinxCoroutinesPlayServices = Dependency("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.jetBrainsCoroutinesVersion}")
    val kotlinxCoroutinesTest = Dependency("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.testJetBrainsCoroutinesVersion}")
    val retrofit = Dependency("com.squareup.retrofit2:retrofit:${Versions.squareupRetrofitVersion}")
    val retrofitConverterGson = Dependency("com.squareup.retrofit2:converter-gson:${Versions.squareupRetrofitVersion}")
    val retrofitOkhttp = Dependency("com.squareup.okhttp3:okhttp:${Versions.squareupOkHttpVersion}")
    val retrofitLoggingInterceptor = Dependency("com.squareup.okhttp3:logging-interceptor:${Versions.squareupOkHttpVersion}")
    val truth = Dependency("com.google.truth:truth:${Versions.testGoogleTruthVersion}")
    val junit = Dependency("junit:junit:${Versions.testJunitVersion}")
    val espressoCore = Dependency("androidx.test.espresso:espresso-core:${Versions.testAndroidxTestEspresso}")
    val extJunit = Dependency("androidx.test.ext:junit-ktx:${Versions.testAndroidxTestJunitVersion}")
    val chuckLibrary = Dependency("com.readystatesoftware.chuck:library:${Versions.chuckVersion}")
    val chuckLibraryNoOp = Dependency("com.readystatesoftware.chuck:library-no-op:${Versions.chuckVersion}")

    // google-firebase
    const val googleAuth = "com.google.android.gms:play-services-auth:${Versions.googleAuthVersion}"
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseVersion}"
    const val firebasePerf = "com.google.firebase:firebase-perf-ktx"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseDatabase = "com.google.firebase:firebase-database-ktx"
    const val firebaseStorage = "com.google.firebase:firebase-storage-ktx"
    const val firebaseFirestore = "com.google.firebase:firebase-firestore-ktx"
    const val firebaseAuth = "com.google.firebase:firebase-auth-ktx"
    const val firebaseUIAuth = "com.firebaseui:firebase-ui-auth:${Versions.firebaseUIVersion}"
    const val firebaseUIDatabase = "com.firebaseui:firebase-ui-database:${Versions.firebaseUIVersion}"
}
