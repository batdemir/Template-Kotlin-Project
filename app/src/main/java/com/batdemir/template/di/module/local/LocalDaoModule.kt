package com.batdemir.template.di.module.local

import com.batdemir.template.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object LocalDaoModule {
    @Provides
    fun provideDaoGithub(db: AppDatabase) = db.githubDao()

    @Provides
    fun provideDaoStackOverFlow(db: AppDatabase) = db.stackOverFlowDao()
}