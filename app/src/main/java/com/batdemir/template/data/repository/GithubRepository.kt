package com.batdemir.template.data.repository

import com.batdemir.template.data.local.datasource.GithubLocalDataSource
import com.batdemir.template.data.remote.datasource.GithubUserRemoteDataSource
import com.batdemir.template.utils.performGetOperation
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val localDataSource: GithubLocalDataSource,
    private val remoteDataSource: GithubUserRemoteDataSource
) {
    fun getUsers() = performGetOperation(
        databaseQuery = { localDataSource.get() },
        networkCall = { remoteDataSource.getUsers() },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getUser(user: String) = performGetOperation(networkCall = { remoteDataSource.getUser(user) })
}