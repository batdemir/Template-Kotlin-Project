package com.batdemir.template.data.local.base

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.batdemir.template.data.entities.RecyclerItem

/**
 * @param T
 */
interface BaseDao<T : RecyclerItem> {
    fun get(): LiveData<List<T>>
    fun get(id: Long): LiveData<List<T>>
    fun getPaging(): PagingSource<Int, T>
    suspend fun insert(model: T)
    suspend fun update(model: T)
    suspend fun delete(model: T)
    suspend fun deleteAll()
}
