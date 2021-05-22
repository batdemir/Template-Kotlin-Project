package com.batdemir.template.data.local.base

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*

/**
 * BaseDao
 *
 * @param T type of model,
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
