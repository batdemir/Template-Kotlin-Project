package com.batdemir.template.di.module.remote

import com.batdemir.template.data.remote.datasource.GithubUserRemoteDataSource
import com.batdemir.template.data.remote.datasource.StackOverFlowRemoteDataSource
import com.batdemir.template.data.remote.service.GithubService
import com.batdemir.template.data.remote.service.StackOverFlowService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RemoteDataSourceModule {
    @Singleton
    @Provides
    fun provideRemoteDataSourceGitHubUser(service: GithubService) =
        GithubUserRemoteDataSource(service)

    @Singleton
    @Provides
    fun provideRemoteDataSourceStackOverFlowUser(service: StackOverFlowService) =
        StackOverFlowRemoteDataSource(service)
}