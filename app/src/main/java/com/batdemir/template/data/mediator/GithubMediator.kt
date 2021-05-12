package com.batdemir.template.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.batdemir.template.data.entities.db.GithubUser
import com.batdemir.template.data.local.dao.GithubDao
import com.batdemir.template.data.remote.service.GithubService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class GithubMediator @Inject constructor(
    private val dao: GithubDao,
    private val service: GithubService
) : RemoteMediator<Int, GithubUser>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, GithubUser>): MediatorResult {
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
                since = null,
                perPage = loadKey
            )
            response.forEach {
                dao.insert(it)
            }
            MediatorResult.Success(
                endOfPaginationReached = response.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}