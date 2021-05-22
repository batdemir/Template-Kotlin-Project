package com.batdemir.template.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.batdemir.template.data.entities.db.GithubUser
import com.batdemir.template.data.local.base.BaseDao

@Dao
abstract class GithubDao : BaseDao<GithubUser>() {
    @Query("SELECT * FROM GithubUser")
    abstract override fun get(): LiveData<List<GithubUser>>

    @Query("SELECT * FROM GithubUser WHERE github_id=:id")
    abstract override fun get(id: Long): LiveData<List<GithubUser>>

    @Query("SELECT * FROM GithubUser")
    abstract override fun getPaging(): PagingSource<Int, GithubUser>

    @Query("DELETE FROM GithubUser")
    abstract override suspend fun deleteAll()
}