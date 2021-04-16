package com.batdemir.template.data.repository

import com.batdemir.template.data.remote.datasource.GithubUserRemoteDataSource
import com.batdemir.template.utils.performGetOperation
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val githubUserRemoteDataSource: GithubUserRemoteDataSource
) {
    fun getUsers() = performGetOperation(networkCall = { githubUserRemoteDataSource.getUsers() })

    fun getUser(user: String) = performGetOperation(networkCall = { githubUserRemoteDataSource.getUser(user) })
}