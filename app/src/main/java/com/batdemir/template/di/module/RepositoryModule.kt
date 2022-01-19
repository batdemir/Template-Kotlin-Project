package com.batdemir.template.di.module

import com.batdemir.template.data.local.datasource.GithubLocalDataSource
import com.batdemir.template.data.local.datasource.StackOverFlowLocalDataSource
import com.batdemir.template.data.remote.datasource.GithubUserRemoteDataSource
import com.batdemir.template.data.remote.datasource.StackOverFlowRemoteDataSource
import com.batdemir.template.data.repository.GithubRepository
import com.batdemir.template.data.repository.StackOverFlowRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    fun provideRepositoryGithub(
        localDataSource: GithubLocalDataSource,
        remoteDataSource: GithubUserRemoteDataSource
    ) = GithubRepository(localDataSource, remoteDataSource)

    @Provides
    fun provideRepositoryStackOverFlow(
        localDataSource: StackOverFlowLocalDataSource,
        remoteDataSource: StackOverFlowRemoteDataSource
    ) = StackOverFlowRepository(localDataSource, remoteDataSource)
}