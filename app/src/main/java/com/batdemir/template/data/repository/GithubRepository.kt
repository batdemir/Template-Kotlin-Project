package com.batdemir.template.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.batdemir.template.data.Constant
import com.batdemir.template.data.entities.db.GithubUser
import com.batdemir.template.data.remote.datasource.GithubUserRemoteDataSource
import com.batdemir.template.data.remote.datasource.paging.GithubSearchParams
import com.batdemir.template.data.remote.datasource.paging.GithubUserPagingRemoteDataSource
import com.batdemir.template.data.remote.service.GithubService
import com.batdemir.template.utils.performGetOperation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val service: GithubService,
    private val remoteDataSource: GithubUserRemoteDataSource
) {
    fun getUsersPaging(searchParams: GithubSearchParams): Flow<PagingData<GithubUser>> = Pager(
        config = PagingConfig(pageSize = Constant.NETWORK_PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { GithubUserPagingRemoteDataSource(service, searchParams) }
    ).flow

    fun getUsers() = performGetOperation(networkCall = { remoteDataSource.getUsers() })

    fun getUser(user: String) = performGetOperation(networkCall = { remoteDataSource.getUser(user) })
}