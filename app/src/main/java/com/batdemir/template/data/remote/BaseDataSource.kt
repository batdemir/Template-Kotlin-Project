package com.batdemir.template.data.remote

import com.batdemir.template.data.entities.Resource
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                return if (body != null) Resource.success(body)
                else error(response.errorBody().toString())
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String?): Resource<T> {
        Timber.d(message)
        return Resource.error(Throwable("Network call has failed for a following reason: $message"))
    }
}