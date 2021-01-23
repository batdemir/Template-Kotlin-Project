package com.batdemir.template.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.batdemir.template.data.entities.db.Example
import com.batdemir.template.data.local.dao.ExampleDao

@Database(
    entities = [
        Example::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "batdemir")
                .fallbackToDestructiveMigration()
                .build()
    }


    abstract fun exampleDao(): ExampleDao
}