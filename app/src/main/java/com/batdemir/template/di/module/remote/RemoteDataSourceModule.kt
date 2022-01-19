package com.batdemir.template.di.module.remote

import com.batdemir.template.data.remote.datasource.GithubUserRemoteDataSource
import com.batdemir.template.data.remote.datasource.StackOverFlowRemoteDataSource
import com.batdemir.template.data.remote.service.GithubService
import com.batdemir.template.data.remote.service.StackOverFlowService
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