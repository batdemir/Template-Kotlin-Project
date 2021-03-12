package com.batdemir.template.di.module

import com.batdemir.template.data.local.datasource.ExampleLocalDataSource
import com.batdemir.template.data.remote.datasource.ExampleRemoteDataSource
import com.batdemir.template.data.remote.datasource.GithubUserRemoteDataSource
import com.batdemir.template.data.remote.datasource.StackOverFlowRemoteDataSource
import com.batdemir.template.data.repository.ExampleRepository
import com.batdemir.template.data.repository.GithubRepository
import com.batdemir.template.data.repository.StackOverFlowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepositoryExample(
        remoteDataSource: ExampleRemoteDataSource,
        localeDataSource: ExampleLocalDataSource,
    ) =
        ExampleRepository(remoteDataSource, localeDataSource)

    @Singleton
    @Provides
    fun provideRepositoryGithub(
        remoteDataSource: GithubUserRemoteDataSource
    ) =
        GithubRepository(remoteDataSource)

    @Singleton
    @Provides
    fun provideRepositoryStackOverFlow(
        remoteDataSource: StackOverFlowRemoteDataSource
    ) =
        StackOverFlowRepository(remoteDataSource)
}