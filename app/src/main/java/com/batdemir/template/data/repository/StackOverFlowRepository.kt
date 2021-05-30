package com.batdemir.template.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.batdemir.template.data.Constant
import com.batdemir.template.data.entities.db.StackOverFlowUser
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.data.local.datasource.StackOverFlowLocalDataSource
import com.batdemir.template.data.mediator.StackOverFlowMediatorDataSource
import com.batdemir.template.data.paging.StackOverFlowPagingRemoteDataSource
import com.batdemir.template.data.paging.StackOverFlowSearchParams
import com.batdemir.template.data.remote.datasource.StackOverFlowRemoteDataSource
import com.batdemir.template.utils.performGetOperation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StackOverFlowRepository @Inject constructor(
    private val localDataSource: StackOverFlowLocalDataSource,
    private val remoteDataSource: StackOverFlowRemoteDataSource
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getUsersMediator(): Flow<PagingData<StackOverFlowUser>> = Pager(
        config = PagingConfig(pageSize = Constant.NETWORK_PAGE_SIZE, enablePlaceholders = false),
        remoteMediator = StackOverFlowMediatorDataSource(localDataSource, remoteDataSource),
        pagingSourceFactory = { localDataSource.getPaging() }
    ).flow

    fun getUsersPaging(searchParams: StackOverFlowSearchParams): Flow<PagingData<ActionItemModel>> = Pager(
        config = PagingConfig(pageSize = Constant.NETWORK_PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { StackOverFlowPagingRemoteDataSource(remoteDataSource, searchParams) }
    ).flow

    fun getUsersRemoteAndLocal() = performGetOperation(
        databaseQuery = { localDataSource.get() },
        networkCall = { remoteDataSource.getUsers() },
        saveCallResult = { localDataSource.insert(it.items) }
    )

    fun getUsers() = performGetOperation(networkCall = { remoteDataSource.getUsers() })

    fun getUser(user: String) = performGetOperation(networkCall = { remoteDataSource.getUser(user) })
}