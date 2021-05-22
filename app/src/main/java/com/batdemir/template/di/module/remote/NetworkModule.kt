package com.batdemir.template.di.module.remote

import android.content.Context
import com.batdemir.template.BuildConfig
import com.batdemir.template.data.Constant
import com.batdemir.template.di.module.remote.interceptor.*
import com.batdemir.template.utils.DateFormat
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(
    includes = [
        RemoteDataSourceModule::class,
        RemoteServiceModule::class
    ]
)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(
        hostSelectionInterceptor: HostSelectionInterceptor
    ): OkHttpClient.Builder = OkHttpClient.Builder()
        .readTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(Constant.TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(hostSelectionInterceptor)

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory = GsonConverterFactory
        .create(
            GsonBuilder()
                .setDateFormat(DateFormat.DEFAULT_DATE_FORMAT.toString())
                .create()
        )

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun provideHostSelectionInterceptor(): HostSelectionInterceptor = HostSelectionInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        context: Context,
        builder: OkHttpClient.Builder,
        interceptor: HttpLoggingInterceptor,
        acceptLanguageInterceptor: AcceptLanguageInterceptor,
        authInterceptor: AuthInterceptor,
        encodingInterceptor: EncodingInterceptor,
        userAgentInterceptor: UserAgentInterceptor
    ): OkHttpClient {
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addNetworkInterceptor(interceptor)
            builder.addInterceptor(ChuckInterceptor(context))
        }
        return builder.build()
    }
}