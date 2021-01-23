package com.batdemir.template.data.remote.datasource

import com.batdemir.template.data.entities.db.Example
import com.batdemir.template.data.remote.BaseDataSource
import com.batdemir.template.data.remote.service.ExampleService
import javax.inject.Inject

class ExampleRemoteDataSource @Inject constructor(
    private val service: ExampleService
) : BaseDataSource() {
    suspend fun get() = getResult { service.get() }

    suspend fun get(id: Long) = getResult { service.get(id) }

    suspend fun add(model: Example) = getResult { service.add(model) }

    suspend fun delete(model: Example) = getResult { service.delete(model) }

    suspend fun update(model: Example) = getResult { service.update(model) }
}