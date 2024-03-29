package com.batdemir.template.features.github

import com.batdemir.template.models.db.GithubUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("users")
    suspend fun getUsers(
        @Query("since") since: Long? = null,
        @Query("per_page") perPage: Long? = null,
    ): Response<List<GithubUser>>

    @GET("users/{user}")
    suspend fun getUser(@Path("user") user: String): Response<GithubUser>
}
