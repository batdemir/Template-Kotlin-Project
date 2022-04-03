package com.batdemir.template.features.github

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.batdemir.template.models.db.GithubUser
import com.bumptech.glide.load.HttpException
import java.io.IOException

@ExperimentalPagingApi
class GithubMediatorDataSource(
    private val localDataSource: GithubLocalDataSource,
    private val remoteDataSource: GithubUserRemoteDataSource
) : RemoteMediator<Int, GithubUser>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GithubUser>
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
                since = null,
                perPage = loadKey
            )
            localDataSource.insert(response.data ?: listOf())
            MediatorResult.Success(
                endOfPaginationReached = response.data.isNullOrEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
