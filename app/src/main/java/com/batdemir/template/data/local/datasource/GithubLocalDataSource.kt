package com.batdemir.template.data.local.datasource

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.batdemir.template.data.entities.db.GithubUser
import com.batdemir.template.data.local.base.BaseDataSource
import com.batdemir.template.data.local.dao.GithubDao
import javax.inject.Inject

class GithubLocalDataSource @Inject constructor(
    private val dao: GithubDao
) : BaseDataSource<GithubUser> {
    override fun get(): LiveData<List<GithubUser>> {
        return dao.get()
    }

    override fun get(id: Long): LiveData<List<GithubUser>> {
        return dao.get(id)
    }

    override fun getPaging(): PagingSource<Int, GithubUser> {
        return dao.getPaging()
    }

    override suspend fun insert(model: GithubUser) {
        dao.insert(model)
    }

    override suspend fun delete(model: GithubUser) {
        dao.delete(model)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun update(model: GithubUser) {
        dao.update(model)
    }
}