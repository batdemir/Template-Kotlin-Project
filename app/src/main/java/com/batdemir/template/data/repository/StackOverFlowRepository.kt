package com.batdemir.template.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.batdemir.template.data.Constant
import com.batdemir.template.data.entities.ui.ActionItemModel
import com.batdemir.template.data.remote.datasource.StackOverFlowRemoteDataSource
import com.batdemir.template.data.remote.datasource.paging.StackOverFlowPagingRemoteDataSource
import com.batdemir.template.data.remote.datasource.paging.StackOverFlowSearchParams
import com.batdemir.template.data.remote.service.StackOverFlowService
import com.batdemir.template.utils.performGetOperation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StackOverFlowRepository @Inject constructor(
    private val service: StackOverFlowService,
    private val remoteDataSource: StackOverFlowRemoteDataSource
) {
    fun getUsersPaging(searchParams: StackOverFlowSearchParams): Flow<PagingData<ActionItemModel>> = Pager(
        config = PagingConfig(pageSize = Constant.NETWORK_PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { StackOverFlowPagingRemoteDataSource(service, searchParams) }
    ).flow

    fun getUsers() = performGetOperation(networkCall = { remoteDataSource.getUsers() })

    fun getUser(user: String) = performGetOperation(networkCall = { remoteDataSource.getUser(user) })
}