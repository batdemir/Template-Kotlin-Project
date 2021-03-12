package com.batdemir.template.data.remote.service

import com.batdemir.template.data.entities.db.GithubUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users")
    suspend fun getUsers(): Response<List<GithubUser>>

    @GET("users/{user}")
    suspend fun getUser(@Path("user") user: String): Response<GithubUser>
}