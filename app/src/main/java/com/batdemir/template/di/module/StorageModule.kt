package com.batdemir.template.di.module

import com.batdemir.template.data.storage.SharedPreferencesStorage
import com.batdemir.template.data.storage.Storage
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
}