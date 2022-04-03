package com.batdemir.oauth2.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.batdemir.core.models.Resource
import com.batdemir.oauth2.features.token.TokenRepository
import com.batdemir.oauth2.other.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseDataSourceToken(private val tokenRepository: TokenRepository) {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (response.code() == SUCCESS_CODE_204) {
                    return Resource.success(body ?: Any::class.java.newInstance() as T)
                }
                return if (body != null) Resource.success(body)
                else error(response.errorBody().toString())
            }
            if (response.code() == ERROR_CODE_401) {
                for (i in Constant.NUMBER_ZERO..TRY_COUNT) {
                    if (tokenRepository.getToken(tokenRepository.getRefreshTokenRequest()).await().status == Resource.Status.SUCCESS) {
                        return getResult(call)
                    }
                }
                error<T>(MESSAGE_AUTH)
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String?): Resource<T> {
        return Resource.error(Throwable("$MESSAGE_UNHANDLED $message"))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun <T> LiveData<Resource<T>>.await() = withContext(Dispatchers.Main.immediate) {
        suspendCancellableCoroutine<Resource<T>> {
            val observer = object : Observer<Resource<T>> {
                override fun onChanged(value: Resource<T>) {
                    if (value.status != Resource.Status.LOADING) {
                        removeObserver(this)
                        it.resume(value, null)
                    }
                }
            }
            observeForever(observer)
            it.invokeOnCancellation { removeObserver(observer) }
        }
    }

    companion object {
        private const val TRY_COUNT = 3
        private const val SUCCESS_CODE_204 = 204
        private const val ERROR_CODE_401 = 401
        private const val MESSAGE_AUTH = "auth exception"
        private const val MESSAGE_UNHANDLED = "Network call has failed for a following reason:"
    }
}
