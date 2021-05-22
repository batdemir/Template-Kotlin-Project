package com.batdemir.template.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.batdemir.template.data.entities.db.StackOverFlowUser
import com.batdemir.template.data.local.base.BaseDao

@Dao
abstract class StackOverFlowDao : BaseDao<StackOverFlowUser>() {
    @Query("SELECT * FROM StackOverFlowUser")
    abstract override fun get(): LiveData<List<StackOverFlowUser>>

    @Query("SELECT * FROM StackOverFlowUser WHERE stack_over_flow_id=:id")
    abstract override fun get(id: Long): LiveData<List<StackOverFlowUser>>

    @Query("SELECT * FROM StackOverFlowUser")
    abstract override fun getPaging(): PagingSource<Int, StackOverFlowUser>

    @Query("DELETE FROM StackOverFlowUser")
    abstract override suspend fun deleteAll()
}