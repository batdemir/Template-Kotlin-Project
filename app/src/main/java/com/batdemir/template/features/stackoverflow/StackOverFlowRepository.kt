package com.batdemir.template.features.stackoverflow

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.batdemir.core.utils.performGetOperation
import com.batdemir.template.models.db.StackOverFlowUser
import com.batdemir.template.models.ui.ActionItemModel
import com.batdemir.template.other.Constant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StackOverFlowRepository @Inject constructor(
    private val localDataSource: StackOverFlowLocalDataSource,
    private val remoteDataSource: StackOverFlowRemoteDataSource
) {
    @ExperimentalPagingApi
    fun getUsersMediator(): Flow<PagingData<StackOverFlowUser>> = Pager(
        config = PagingConfig(pageSize = Constant.NETWORK_PAGE_SIZE, enablePlaceholders = false),
        remoteMediator = StackOverFlowMediatorDataSource(localDataSource, remoteDataSource),
        pagingSourceFactory = { localDataSource.getPaging() }
    ).flow

    fun getUsersPaging(searchParams: StackOverFlowSearchParams): Flow<PagingData<ActionItemModel>> =
        Pager(
            config = PagingConfig(
                pageSize = Constant.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                StackOverFlowPagingRemoteDataSource(
                    remoteDataSource,
                    searchParams
                )
            }
        ).flow

    fun getUsersRemoteAndLocal() = performGetOperation(
        databaseQuery = { localDataSource.get() },
        networkCall = { remoteDataSource.getUsers() },
        saveCallResult = { localDataSource.insert(it.items) }
    )

    fun getUsers() = performGetOperation(networkCall = { remoteDataSource.getUsers() })

    fun getUser(user: String) =
        performGetOperation(networkCall = { remoteDataSource.getUser(user) })
}