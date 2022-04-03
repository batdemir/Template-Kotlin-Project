package com.batdemir.template.di

import com.batdemir.template.features.github.GithubLocalDataSource
import com.batdemir.template.features.github.GithubRepository
import com.batdemir.template.features.github.GithubUserRemoteDataSource
import com.batdemir.template.features.stackoverflow.StackOverFlowLocalDataSource
import com.batdemir.template.features.stackoverflow.StackOverFlowRemoteDataSource
import com.batdemir.template.features.stackoverflow.StackOverFlowRepository
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
