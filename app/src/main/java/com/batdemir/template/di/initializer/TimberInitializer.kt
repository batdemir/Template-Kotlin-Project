package com.batdemir.template.di.initializer

import com.batdemir.template.BuildConfig
import com.batdemir.template.app.MyApplication
import timber.log.Timber
import javax.inject.Inject

class TimberInitializer @Inject constructor(
) : Initializer {
    override fun initialize(application: MyApplication) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}