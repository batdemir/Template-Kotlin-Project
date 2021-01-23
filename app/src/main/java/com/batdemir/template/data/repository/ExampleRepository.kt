package com.batdemir.template.data.repository

import com.batdemir.template.data.local.datasource.ExampleLocalDataSource
import com.batdemir.template.data.remote.datasource.ExampleRemoteDataSource
import com.batdemir.template.utils.performGetOperation
import javax.inject.Inject

class ExampleRepository @Inject constructor(
    private val remoteDataSource: ExampleRemoteDataSource,
    private val localDataSource: ExampleLocalDataSource
) {
    fun get() = performGetOperation(
        databaseQuery = { localDataSource.get() },
        networkCall = { remoteDataSource.get() },
        saveCallResult = {
            it.forEach { x ->
                localDataSource.add(x)
            }
        }
    )
}