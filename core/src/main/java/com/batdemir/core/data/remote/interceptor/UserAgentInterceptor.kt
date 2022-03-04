package com.batdemir.core.data.remote.interceptor

import android.content.Context
import com.batdemir.core.utils.NetworkUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserAgentInterceptor @Inject constructor(
    @ApplicationContext context: Context
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