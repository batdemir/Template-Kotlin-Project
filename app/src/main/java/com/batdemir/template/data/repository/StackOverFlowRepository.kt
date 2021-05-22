package com.batdemir.template.data.repository

import com.batdemir.template.data.remote.datasource.StackOverFlowRemoteDataSource
import com.batdemir.template.utils.performGetOperation
import javax.inject.Inject

class StackOverFlowRepository @Inject constructor(
    private val remoteDataSource: StackOverFlowRemoteDataSource
) {
    fun getUsers() = performGetOperation(networkCall = { remoteDataSource.getUsers() })

    fun getUser(user: String) = performGetOperation(networkCall = { remoteDataSource.getUser(user) })
}