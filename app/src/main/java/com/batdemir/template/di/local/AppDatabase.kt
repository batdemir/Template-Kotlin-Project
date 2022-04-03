package com.batdemir.template.di.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.batdemir.template.features.github.GithubDao
import com.batdemir.template.features.stackoverflow.StackOverFlowDao
import com.batdemir.template.models.db.GithubUser
import com.batdemir.template.models.db.StackOverFlowUser

@Database(
    entities = [
        GithubUser::class,
        StackOverFlowUser::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun githubDao(): GithubDao
    abstract fun stackOverFlowDao(): StackOverFlowDao
}
