package com.batdemir.core.data.local

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource

/**
 * BaseDataSource
 *
 * @param T type of model
 */
interface BaseDataSource<T : Any> {
    fun get(): LiveData<List<T>>
    fun get(id: Long): LiveData<List<T>>
    fun getPaging(): PagingSource<Int, T>
    suspend fun deleteAll()
    suspend fun insert(model: T): Long
    suspend fun insert(models: List<T>)
    suspend fun update(model: T)
    suspend fun delete(model: T)
}