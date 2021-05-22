package com.batdemir.template.di.module

import com.batdemir.template.data.remote.datasource.GithubUserRemoteDataSource
import com.batdemir.template.data.remote.datasource.StackOverFlowRemoteDataSource
import com.batdemir.template.data.remote.service.GithubService
import com.batdemir.template.data.remote.service.StackOverFlowService
import com.batdemir.template.data.repository.GithubRepository
import com.batdemir.template.data.repository.StackOverFlowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepositoryGithub(
        service: GithubService,
        remoteDataSource: GithubUserRemoteDataSource
    ) = GithubRepository(service, remoteDataSource)

    @Singleton
    @Provides
    fun provideRepositoryStackOverFlow(
        service: StackOverFlowService,
        remoteDataSource: StackOverFlowRemoteDataSource
    ) = StackOverFlowRepository(service, remoteDataSource)
}