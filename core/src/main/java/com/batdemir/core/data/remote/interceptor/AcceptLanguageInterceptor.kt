package com.batdemir.core.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AcceptLanguageInterceptor @Inject constructor() : Interceptor {
    private var mLanguage: String = Locale.getDefault().language
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .addHeader(
                name = "Accept-Language",
                value = mLanguage
            )
            .build()
        return chain.proceed(request)
    }
}
