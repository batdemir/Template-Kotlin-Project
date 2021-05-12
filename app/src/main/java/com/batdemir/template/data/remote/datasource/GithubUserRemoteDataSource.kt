package com.batdemir.template.data.remote.datasource

import com.batdemir.template.data.remote.base.BaseDataSource
import com.batdemir.template.data.remote.service.GithubService
import javax.inject.Inject

class GithubUserRemoteDataSource @Inject constructor(
    private val githubService: GithubService
) : BaseDataSource() {
    suspend fun getUsers() = getResult { githubService.getUsers() }

    suspend fun getUser(user: String) = getResult { githubService.getUser(user) }
}