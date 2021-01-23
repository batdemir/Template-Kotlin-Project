package com.batdemir.template.di.module

import com.batdemir.template.data.remote.datasource.ExampleRemoteDataSource
import com.batdemir.template.data.remote.service.ExampleService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    private const val BASE_URL = ""

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
    }

    //////////////////////////////////////////////////////

    @Singleton
    @Provides
    fun provideServiceExample(retrofit: Retrofit): ExampleService =
        retrofit.create(ExampleService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSourceExample(service: ExampleService) =
        ExampleRemoteDataSource(service)

    //////////////////////////////////////////////////////
}