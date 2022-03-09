package com.batdemir.template.features.stackoverflow

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.batdemir.template.models.db.StackOverFlowUser
import com.batdemir.template.other.StackOverFlowOrderType
import com.batdemir.template.other.StackOverFlowSortType
import com.bumptech.glide.load.HttpException
import java.io.IOException

@ExperimentalPagingApi
class StackOverFlowMediatorDataSource(
    private val localDataSource: StackOverFlowLocalDataSource,
    private val remoteDataSource: StackOverFlowRemoteDataSource
) : RemoteMediator<Int, StackOverFlowUser>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, StackOverFlowUser>
    ): MediatorResult {
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