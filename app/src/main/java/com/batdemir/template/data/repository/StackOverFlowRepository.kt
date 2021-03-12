package com.batdemir.template.data.repository

import com.batdemir.template.data.remote.datasource.StackOverFlowRemoteDataSource
import javax.inject.Inject

class StackOverFlowRepository @Inject constructor(
    private val stackOverFlowRemoteDataSource: StackOverFlowRemoteDataSource
) {
    fun getUsers() = stackOverFlowRemoteDataSource.getUsers()

    fun getUser(user: String) = stackOverFlowRemoteDataSource.getUser(user)
}