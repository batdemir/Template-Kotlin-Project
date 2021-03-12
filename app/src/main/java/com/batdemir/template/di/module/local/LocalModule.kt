package com.batdemir.template.di.module.local

import com.batdemir.template.app.MyApplication
import com.batdemir.template.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        LocalDaoModule::class,
        LocalDataSourceModule::class
    ]
)
object LocalModule {
    @Singleton
    @Provides
    fun provideDatabase(application: MyApplication) = AppDatabase.getDatabase(application)
}