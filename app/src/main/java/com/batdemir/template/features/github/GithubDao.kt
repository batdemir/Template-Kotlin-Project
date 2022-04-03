package com.batdemir.template.features.github

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.batdemir.core.data.local.BaseDao
import com.batdemir.template.models.db.GithubUser

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
