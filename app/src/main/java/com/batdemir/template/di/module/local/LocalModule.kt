package com.batdemir.template.di.module.local

import android.content.Context
import androidx.room.Room
import com.batdemir.template.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        LocalDaoModule::class,
        LocalDataSourceModule::class
    ]
)
object LocalModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "batdemir.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}