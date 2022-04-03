package com.batdemir.template.features.github

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.batdemir.core.utils.performGetOperation
import com.batdemir.template.models.db.GithubUser
import com.batdemir.template.models.ui.ActionItemModel
import com.batdemir.template.other.Constant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val localDataSource: GithubLocalDataSource,
    private val remoteDataSource: GithubUserRemoteDataSource
) {
    @ExperimentalPagingApi
    fun getUsersMediator(): Flow<PagingData<GithubUser>> = Pager(
        config = PagingConfig(pageSize = Constant.NETWORK_PAGE_SIZE, enablePlaceholders = false),
        remoteMediator = GithubMediatorDataSource(localDataSource, remoteDataSource),
        pagingSourceFactory = { localDataSource.getPaging() }
    ).flow

    fun getUsersPaging(searchParams: GithubSearchParams): Flow<PagingData<ActionItemModel>> = Pager(
        config = PagingConfig(pageSize = Constant.NETWORK_PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { GithubUserPagingRemoteDataSource(remoteDataSource, searchParams) }
    ).flow

    fun getUsersRemoteAndLocal() = performGetOperation(
        databaseQuery = { localDataSource.get() },
        networkCall = { remoteDataSource.getUsers() },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getUsers() = performGetOperation(networkCall = { remoteDataSource.getUsers() })
    fun getUser(user: String) = performGetOperation(networkCall = { remoteDataSource.getUser(user) })
}
