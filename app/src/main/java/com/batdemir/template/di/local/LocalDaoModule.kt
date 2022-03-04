package com.batdemir.template.di.local

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