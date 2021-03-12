package com.batdemir.template.di.module.remote.interceptor

import android.content.Context
import com.batdemir.template.utils.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class UserAgentInterceptor @Inject constructor(
    context: Context
) : Interceptor {
    private val mUserAgent: String = NetworkUtil.getUserAgent(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .addHeader("User-Agent", mUserAgent)
            .build()
        return chain.proceed(request)
    }
}