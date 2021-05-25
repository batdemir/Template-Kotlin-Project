package com.batdemir.template.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.batdemir.template.data.StackOverFlowOrderType
import com.batdemir.template.data.StackOverFlowSortType
import com.batdemir.template.data.entities.db.StackOverFlowUser
import com.batdemir.template.data.local.datasource.StackOverFlowLocalDataSource
import com.batdemir.template.data.remote.datasource.StackOverFlowRemoteDataSource
import com.bumptech.glide.load.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class StackOverFlowMediatorDataSource(
    private val localDataSource: StackOverFlowLocalDataSource,
    private val remoteDataSource: StackOverFlowRemoteDataSource
) : RemoteMediator<Int, StackOverFlowUser>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, StackOverFlowUser>): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                    lastItem.id
                }
            }
            val response = remoteDataSource.getUsers(
                page = loadKey,
                pageSize = null,
                fromDate = null,
                toDate = null,
                orderType = StackOverFlowOrderType.ASC,
                min = null,
                max = null,
                sortType = StackOverFlowSortType.NAME,
                inName = null
            )
            localDataSource.insert(response.data?.items ?: listOf())
            MediatorResult.Success(
                endOfPaginationReached = response.data?.hasMore ?: false
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}