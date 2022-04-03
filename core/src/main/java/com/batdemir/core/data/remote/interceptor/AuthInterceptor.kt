package com.batdemir.core.data.remote.interceptor

import com.batdemir.core.manager.storage.MyStorageManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val myStorageManager: MyStorageManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val tokenType = myStorageManager.getString(KEY_TOKEN_TYPE)
        val token = myStorageManager.getString(KEY_TOKEN)
        return if (token.isEmpty()) {
            chain.proceed(chain.request())
        } else {
            val request = chain.request()
            val url = request
                .newBuilder()
                .addHeader(
                    name = "Authorization",
                    value = "$tokenType $token"
                )
                .build()
            chain.proceed(url)
        }
    }

    companion object {
        const val KEY_TOKEN_TYPE = "key_token_type"
        const val KEY_TOKEN = "key_token"
    }
}
