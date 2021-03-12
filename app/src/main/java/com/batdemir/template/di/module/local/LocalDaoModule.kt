package com.batdemir.template.di.module.local

import com.batdemir.template.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object LocalDaoModule {
    @Singleton
    @Provides
    fun provideDaoExample(db: AppDatabase) = db.exampleDao()
}