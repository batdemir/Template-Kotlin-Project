package com.batdemir.template.di.module.local

import com.batdemir.template.data.local.dao.ExampleDao
import com.batdemir.template.data.local.datasource.ExampleLocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object LocalDataSourceModule {
    @Singleton
    @Provides
    fun provideLocalDataSourceExample(dao: ExampleDao) = ExampleLocalDataSource(dao)
}