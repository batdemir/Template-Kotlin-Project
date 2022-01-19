package com.batdemir.template.di.module.local

import com.batdemir.template.data.local.dao.GithubDao
import com.batdemir.template.data.local.dao.StackOverFlowDao
import com.batdemir.template.data.local.datasource.GithubLocalDataSource
import com.batdemir.template.data.local.datasource.StackOverFlowLocalDataSource
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