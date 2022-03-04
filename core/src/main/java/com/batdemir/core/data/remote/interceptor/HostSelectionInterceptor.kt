package com.batdemir.core.data.remote.interceptor

import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HostSelectionInterceptor @Inject constructor(
) : Interceptor {
    @Volatile
    private var host: String? = null

    fun setHost(host: String?) {
        this.host = host
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val host = host
        host?.let {
            val newHost = it
                .toHttpUrlOrNull()
                ?.host
            val newUrl = request
                .url
                .newBuilder()
                .host(newHost ?: "")
                .build()
            request = request
                .newBuilder()
                .url(newUrl)
                .build()
        }
        return chain.proceed(request)
    }
}