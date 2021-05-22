package com.batdemir.template.di.module

import com.batdemir.template.data.remote.datasource.GithubUserRemoteDataSource
import com.batdemir.template.data.remote.datasource.StackOverFlowRemoteDataSource
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
        remoteDataSource: GithubUserRemoteDataSource
    ) = GithubRepository(remoteDataSource)

    @Singleton
    @Provides
    fun provideRepositoryStackOverFlow(
        remoteDataSource: StackOverFlowRemoteDataSource
    ) = StackOverFlowRepository(remoteDataSource)
}