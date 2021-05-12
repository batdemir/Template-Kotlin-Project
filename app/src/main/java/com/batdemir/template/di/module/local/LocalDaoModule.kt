package com.batdemir.template.di.module.local

import com.batdemir.template.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object LocalDaoModule {
    @Singleton
    @Provides
    fun provideDaoGithub(db: AppDatabase) = db.githubDao()

    @Singleton
    @Provides
    fun provideDaoStackOverFlow(db: AppDatabase) = db.stackOverFlowDao()
}