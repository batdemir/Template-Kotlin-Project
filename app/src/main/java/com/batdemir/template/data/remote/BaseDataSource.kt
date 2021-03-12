package com.batdemir.template.data.remote

import com.batdemir.template.data.entities.ResourceModel
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ResourceModel<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return ResourceModel.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): ResourceModel<T> {
        Timber.d(message)
        return ResourceModel.error("Network call has failed for a following reason: $message")
    }
}