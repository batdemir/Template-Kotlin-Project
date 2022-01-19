package com.batdemir.template.di.module.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = ""
        return if (token.isEmpty()) {
            chain.proceed(chain.request())
        } else {
            val request = chain.request()
            val url = request
                .newBuilder()
                .addHeader(
                    name = "Authorization",
                    value = "Bearer $token"
                )
                .build()
            chain.proceed(url)
        }
    }
}