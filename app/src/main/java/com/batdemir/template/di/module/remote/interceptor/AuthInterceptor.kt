package com.batdemir.template.di.module.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(

) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String = ""
        return if (token.isEmpty()) {
            chain.proceed(chain.request())
        } else {
            val request = chain.request()
            val url = request
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(url)
        }
    }
}