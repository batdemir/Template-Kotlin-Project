package com.batdemir.template.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.batdemir.template.data.entities.db.Example

@Dao
interface ExampleDao {
    @Query("SELECT * FROM Example")
    fun get(): LiveData<List<Example>>

    @Query("SELECT * FROM Example WHERE id = :id")
    fun get(id: Long): LiveData<List<Example>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(model: Example)

    @Delete
    suspend fun delete(model: Example)

    @Update
    suspend fun update(model: Example)
}