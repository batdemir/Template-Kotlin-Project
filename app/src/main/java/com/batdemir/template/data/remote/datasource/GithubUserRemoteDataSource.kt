package com.batdemir.template.data.remote.datasource

import com.batdemir.template.data.entities.Resource
import com.batdemir.template.data.remote.service.GithubService
import com.batdemir.template.utils.execute
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GithubUserRemoteDataSource @Inject constructor(
    private val githubService: GithubService
) {
    fun getUsers() = flow {
        emit(Resource.Success(githubService.getUsers()))
    }.execute()

    fun getUser(user: String) = flow {
        emit(Resource.Success(githubService.getUser(user)))
    }.execute()
}