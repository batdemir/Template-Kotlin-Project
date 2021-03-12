package com.batdemir.template.data.repository

import com.batdemir.template.data.remote.datasource.GithubUserRemoteDataSource
import javax.inject.Inject

class GithubRepository @Inject constructor(
    private val githubUserRemoteDataSource: GithubUserRemoteDataSource
) {
    fun getUsers() = githubUserRemoteDataSource.getUsers()

    fun getUser(user: String) = githubUserRemoteDataSource.getUser(user)
}