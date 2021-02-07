package com.batdemir.template.di.initializer

import com.batdemir.template.app.MyApplication
import javax.inject.Inject

class AppInitializer @Inject constructor(
    private val initializers: Set<@JvmSuppressWildcards Initializer>
) : Initializer {
    override fun initialize(application: MyApplication) {
        initializers.forEach { initializer ->
            initializer.initialize(application)
        }
    }
}