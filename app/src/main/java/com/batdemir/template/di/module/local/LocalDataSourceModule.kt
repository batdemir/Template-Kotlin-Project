package com.batdemir.template.di.module.local

import com.batdemir.template.data.local.dao.GithubDao
import com.batdemir.template.data.local.dao.StackOverFlowDao
import com.batdemir.template.data.local.datasource.GithubLocalDataSource
import com.batdemir.template.data.local.datasource.StackOverFlowLocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object LocalDataSourceModule {
    @Singleton
    @Provides
    fun provideLocalDataSourceGithub(dao: GithubDao) = GithubLocalDataSource(dao)

    @Singleton
    @Provides
    fun provideLocalDataSourceStackOverFlow(dao: StackOverFlowDao) = StackOverFlowLocalDataSource(dao)
}