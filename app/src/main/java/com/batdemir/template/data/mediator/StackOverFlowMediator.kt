package com.batdemir.template.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.batdemir.template.data.entities.db.StackOverFlowUser
import com.batdemir.template.data.local.dao.StackOverFlowDao
import com.batdemir.template.data.remote.service.StackOverFlowService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class StackOverFlowMediator @Inject constructor(
    private val dao: StackOverFlowDao,
    private val service: StackOverFlowService
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
            val response = service.getUsersPaging(
                page = loadKey,
                pageSize = null,
                fromDate = null,
                toDate = null,
                orderType = "",
                min = null,
                max = null,
                sortType = "",
                inName = null,
                site = ""
            )
            response.items.forEach {
                dao.insert(it)
            }
            MediatorResult.Success(
                endOfPaginationReached = response.hasMore
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}