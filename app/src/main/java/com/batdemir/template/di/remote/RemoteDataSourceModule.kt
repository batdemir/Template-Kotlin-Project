package com.batdemir.template.di.remote

import com.batdemir.template.features.github.GithubService
import com.batdemir.template.features.github.GithubUserRemoteDataSource
import com.batdemir.template.features.stackoverflow.StackOverFlowRemoteDataSource
import com.batdemir.template.features.stackoverflow.StackOverFlowService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RemoteDataSourceModule {
    @Provides
    fun provideRemoteDataSourceGitHubUser(service: GithubService) =
        GithubUserRemoteDataSource(service)

    @Provides
    fun provideRemoteDataSourceStackOverFlowUser(service: StackOverFlowService) =
        StackOverFlowRemoteDataSource(service)
}