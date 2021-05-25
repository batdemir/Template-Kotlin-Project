package com.batdemir.template.data.repository

import com.batdemir.template.data.local.datasource.StackOverFlowLocalDataSource
import com.batdemir.template.data.remote.datasource.StackOverFlowRemoteDataSource
import com.batdemir.template.utils.performGetOperation
import javax.inject.Inject

class StackOverFlowRepository @Inject constructor(
    private val localDataSource: StackOverFlowLocalDataSource,
    private val remoteDataSource: StackOverFlowRemoteDataSource
) {
    fun getUsers() = performGetOperation(
        databaseQuery = { localDataSource.get() },
        networkCall = { remoteDataSource.getUsers() },
        saveCallResult = { localDataSource.insert(it.items) }
    )

    fun getUser(user: String) = performGetOperation(networkCall = { remoteDataSource.getUser(user) })
}