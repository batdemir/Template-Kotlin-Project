package com.batdemir.template.di.remote

import com.batdemir.template.BuildConfig
import com.batdemir.template.features.github.GithubService
import com.batdemir.template.features.stackoverflow.StackOverFlowService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object RemoteServiceModule {
    @Provides
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
