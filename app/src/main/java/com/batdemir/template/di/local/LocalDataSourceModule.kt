package com.batdemir.template.di.local

import com.batdemir.template.features.github.GithubDao
import com.batdemir.template.features.github.GithubLocalDataSource
import com.batdemir.template.features.stackoverflow.StackOverFlowDao
import com.batdemir.template.features.stackoverflow.StackOverFlowLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object LocalDataSourceModule {
    @Provides
    fun provideLocalDataSourceGithub(dao: GithubDao) = GithubLocalDataSource(dao)

    @Provides
    fun provideLocalDataSourceStackOverFlow(dao: StackOverFlowDao) =
        StackOverFlowLocalDataSource(dao)
}