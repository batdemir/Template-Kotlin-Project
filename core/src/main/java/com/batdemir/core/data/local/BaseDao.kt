package com.batdemir.core.data.local

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * BaseDao
 *
 * @param T type of model
 */
@Dao
abstract class BaseDao<T : Any> {
    abstract fun get(): LiveData<List<T>>
    abstract fun get(id: Long): LiveData<List<T>>
    abstract fun getPaging(): PagingSource<Int, T>
    abstract suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(model: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(models: List<T>)

    @Update
    abstract suspend fun update(model: T)

    @Delete
    abstract suspend fun delete(model: T)
}
