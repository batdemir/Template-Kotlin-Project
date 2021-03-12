@file:Suppress("UNCHECKED_CAST")

package com.batdemir.template.utils

import com.batdemir.template.data.entities.Resource
import com.batdemir.template.di.module.remote.exception.Error
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response


fun <V, T : Response<V>> Flow<Resource<T>>.execute(): Flow<Resource<V>> =
    onStart {
        emit(Resource.Loading)
    }.map {
        if (it is Resource.Success) {
            if (it.data.isSuccessful)
                Resource.Success(it.data.body())
            else {
                val error: Error =
                    Gson().fromJson(it.data.errorBody()?.charStream(), Error::class.java)
                error.code = it.data.code()
                throw Exception(error)
            }
        } else (it)
    }.catch { throwable ->
        emit(Resource.Error(throwable))
    }.flowOn(Dispatchers.IO) as Flow<Resource<V>>