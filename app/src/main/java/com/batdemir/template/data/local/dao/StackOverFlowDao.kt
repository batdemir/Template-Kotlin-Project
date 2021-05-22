package com.batdemir.template.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.batdemir.template.data.entities.db.StackOverFlowUser
import com.batdemir.template.data.local.base.BaseDao

@Dao
interface StackOverFlowDao : BaseDao<StackOverFlowUser> {
    @Query("SELECT * FROM StackOverFlowUser ORDER BY stack_over_flow_id")
    override fun get(): LiveData<List<StackOverFlowUser>>

    @Query("SELECT * FROM StackOverFlowUser WHERE stack_over_flow_id = :id ORDER BY stack_over_flow_id")
    override fun get(id: Long): LiveData<List<StackOverFlowUser>>

    @Query("SELECT * FROM StackOverFlowUser ORDER BY stack_over_flow_id")
    override fun getPaging(): PagingSource<Int, StackOverFlowUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(model: StackOverFlowUser)

    @Update
    override suspend fun update(model: StackOverFlowUser)

    @Delete
    override suspend fun delete(model: StackOverFlowUser)

    @Query("DELETE FROM StackOverFlowUser")
    override suspend fun deleteAll()
}