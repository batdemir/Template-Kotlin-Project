package com.batdemir.template.data.repository

import com.batdemir.template.data.remote.datasource.StackOverFlowRemoteDataSource
import com.batdemir.template.utils.performGetOperation
import javax.inject.Inject

class StackOverFlowRepository @Inject constructor(
    private val stackOverFlowRemoteDataSource: StackOverFlowRemoteDataSource
) {
    fun getUsers() = performGetOperation(networkCall = { stackOverFlowRemoteDataSource.getUsers() })

    fun getUser(user: String) = performGetOperation(networkCall = { stackOverFlowRemoteDataSource.getUser(user) })
}