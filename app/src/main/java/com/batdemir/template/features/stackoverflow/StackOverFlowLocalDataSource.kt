package com.batdemir.template.features.stackoverflow

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.batdemir.core.data.local.BaseDataSource
import com.batdemir.template.models.db.StackOverFlowUser
import javax.inject.Inject

class StackOverFlowLocalDataSource @Inject constructor(
    private val dao: StackOverFlowDao
) : BaseDataSource<StackOverFlowUser> {
    override fun get(): LiveData<List<StackOverFlowUser>> = dao.get()
    override fun get(id: Long): LiveData<List<StackOverFlowUser>> = dao.get(id)
    override fun getPaging(): PagingSource<Int, StackOverFlowUser> = dao.getPaging()
    override suspend fun deleteAll() = dao.deleteAll()
    override suspend fun insert(model: StackOverFlowUser): Long = dao.insert(model)
    override suspend fun insert(models: List<StackOverFlowUser>) = dao.insert(models)
    override suspend fun update(model: StackOverFlowUser) = dao.update(model)
    override suspend fun delete(model: StackOverFlowUser) = dao.delete(model)
}
