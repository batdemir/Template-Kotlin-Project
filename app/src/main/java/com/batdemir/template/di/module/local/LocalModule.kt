package com.batdemir.template.di.module.local

import android.content.Context
import androidx.room.Room
import com.batdemir.template.data.local.AppDatabase
import com.batdemir.template.other.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        LocalDaoModule::class,
        LocalDataSourceModule::class
    ]
)
object LocalModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, Constant.APP_DB)
            .fallbackToDestructiveMigration()
            .build()
    }
}