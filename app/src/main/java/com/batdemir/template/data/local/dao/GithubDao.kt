package com.batdemir.template.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.batdemir.template.data.entities.db.GithubUser
import com.batdemir.template.data.local.base.BaseDao

@Dao
interface GithubDao : BaseDao<GithubUser> {
    @Query("SELECT * FROM GithubUser ORDER BY github_id")
    override fun get(): LiveData<List<GithubUser>>

    @Query("SELECT * FROM GithubUser WHERE github_id = :id ORDER BY github_id")
    override fun get(id: Long): LiveData<List<GithubUser>>

    @Query("SELECT * FROM GithubUser ORDER BY github_id")
    override fun getPaging(): PagingSource<Int, GithubUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(model: GithubUser)

    @Update
    override suspend fun update(model: GithubUser)

    @Delete
    override suspend fun delete(model: GithubUser)

    @Query("DELETE FROM GithubUser")
    override suspend fun deleteAll()
}