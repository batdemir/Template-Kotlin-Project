package com.batdemir.core.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EncodingInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain
            .request()
            .url
            .toString()
            .replace("%26", "&")
            .replace("%3D", "=")
            .replace("%3F", "?")
        val request = chain
            .request()
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}
