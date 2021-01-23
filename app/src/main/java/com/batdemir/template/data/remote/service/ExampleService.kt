package com.batdemir.template.data.remote.service

import com.batdemir.template.data.entities.db.Example
import retrofit2.Response
import retrofit2.http.*

interface ExampleService {
    @GET("")
    suspend fun get(): Response<List<Example>>

    @GET("")
    suspend fun get(@Query("id") id: Long): Response<List<Example>>

    @POST("")
    suspend fun add(@Body model: Example): Response<Example>

    @DELETE("")
    suspend fun delete(@Body model: Example): Response<Example>

    @PUT("")
    suspend fun update(@Body model: Example): Response<Example>
}