package com.batdemir.template.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.batdemir.template.data.entities.db.GithubUser
import com.batdemir.template.data.entities.db.StackOverFlowUser
import com.batdemir.template.data.local.dao.GithubDao
import com.batdemir.template.data.local.dao.StackOverFlowDao

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