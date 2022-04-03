package com.batdemir.core.initializer

import android.content.Context
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface InitializerEntryPoint {
    companion object {
        fun resolve(context: Context): InitializerEntryPoint {
            val applicationContext = context.applicationContext ?: throw IllegalStateException()
            return EntryPointAccessors.fromApplication(
                applicationContext,
                InitializerEntryPoint::class.java
            )
        }
    }

    fun inject(initializer: LanguageInitializer)
    fun inject(initializer: ThemeInitializer)
    fun inject(initializer: TimberInitializer)
}
