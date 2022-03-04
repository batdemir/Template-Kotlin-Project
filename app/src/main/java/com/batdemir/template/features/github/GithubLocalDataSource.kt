package com.batdemir.template.features.github

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.batdemir.core.data.local.BaseDataSource
import com.batdemir.template.models.db.GithubUser
import javax.inject.Inject

class GithubLocalDataSource @Inject constructor(
    private val dao: GithubDao
) : BaseDataSource<GithubUser> {
    override fun get(): LiveData<List<GithubUser>> = dao.get()

    override fun get(id: Long): LiveData<List<GithubUser>> = dao.get(id)

    override fun getPaging(): PagingSource<Int, GithubUser> = dao.getPaging()

    override suspend fun deleteAll() = dao.deleteAll()

    override suspend fun insert(model: GithubUser): Long = dao.insert(model)

    override suspend fun insert(models: List<GithubUser>) = dao.insert(models)

    override suspend fun update(model: GithubUser) = dao.update(model)

    override suspend fun delete(model: GithubUser) = dao.delete(model)
}