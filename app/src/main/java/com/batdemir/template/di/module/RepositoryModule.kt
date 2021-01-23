package com.batdemir.template.di.module

import com.batdemir.template.data.local.datasource.ExampleLocalDataSource
import com.batdemir.template.data.remote.datasource.ExampleRemoteDataSource
import com.batdemir.template.data.repository.ExampleRepository
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
}