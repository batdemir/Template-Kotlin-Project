package com.batdemir.template.di.module.remote

import com.batdemir.template.BuildConfig
import com.batdemir.template.data.remote.service.ExampleService
import com.batdemir.template.data.remote.service.GithubService
import com.batdemir.template.data.remote.service.StackOverFlowService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object RemoteServiceModule {
    @Provides
    @Singleton
    fun provideExampleService(
        builder: Retrofit.Builder,
        converterFactory: Converter.Factory,
        client: OkHttpClient
    ): ExampleService {
        return builder
            .baseUrl("")
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
            .create(ExampleService::class.java)
    }

    @Provides
    @Singleton
    fun provideGithubService(
        builder: Retrofit.Builder,
        converterFactory: Converter.Factory,
        client: OkHttpClient
    ): GithubService {
        return builder
            .baseUrl(BuildConfig.GITHUB_API)
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
            .create(GithubService::class.java)
    }

    @Provides
    @Singleton
    fun provideStackOverFlowService(
        builder: Retrofit.Builder,
        converterFactory: Converter.Factory,
        client: OkHttpClient
    ): StackOverFlowService {
        return builder
            .baseUrl(BuildConfig.STACK_OVER_FLOW_API)
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
            .create(StackOverFlowService::class.java)
    }
}