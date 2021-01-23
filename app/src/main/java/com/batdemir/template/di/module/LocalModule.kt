package com.batdemir.template.di.module

import android.content.Context
import com.batdemir.template.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object LocalModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context) = AppDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideDaoExample(db: AppDatabase) = db.exampleDao()
}