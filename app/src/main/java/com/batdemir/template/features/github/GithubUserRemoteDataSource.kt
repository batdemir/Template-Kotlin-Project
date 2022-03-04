package com.batdemir.template.features.github

import com.batdemir.core.data.remote.BaseDataSource
import javax.inject.Inject

class GithubUserRemoteDataSource @Inject constructor(
    private val githubService: GithubService
) : BaseDataSource() {
    suspend fun getUsers(since: Long? = null, perPage: Long? = null) =
        getResult { githubService.getUsers(since, perPage) }

    suspend fun getUser(user: String) = getResult { githubService.getUser(user) }
}