@file:SuppressWarnings("kotlin:S107")
package com.batdemir.template.data.remote.service

import com.batdemir.template.data.entities.db.StackOverFlowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StackOverFlowService {
    @GET("/2.2/users")
    suspend fun getUsers(
        @Query("page") page: Long? = null,
        @Query("pagesize") pageSize: Long? = null,
        @Query("fromdate") fromDate: Long? = null,
        @Query("todate") toDate: Long? = null,
        @Query("order") orderType: String,
        @Query("min") min: Long? = null,
        @Query("max") max: Long? = null,
        @Query("sort") sortType: String,
        @Query("inname") inName: String? = null,
        @Query("site") site: String = "stackoverflow"
    ): Response<StackOverFlowResponse>

    @GET("/2.2/{user}")
    suspend fun getUser(
        @Path("user") user: String = "users",
        @Query("page") page: Long? = null,
        @Query("pagesize") pageSize: Long? = null,
        @Query("fromdate") fromDate: Long? = null,
        @Query("todate") toDate: Long? = null,
        @Query("order") orderType: String,
        @Query("min") min: Long? = null,
        @Query("max") max: Long? = null,
        @Query("sort") sortType: String,
        @Query("inname") inName: String? = null,
        @Query("site") site: String = "stackoverflow"
    ): Response<StackOverFlowResponse>
}