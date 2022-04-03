package com.batdemir.oauth2.di.remote

import com.batdemir.oauth2.BuildConfig
import com.batdemir.oauth2.features.auth.AuthService
import com.batdemir.oauth2.features.token.TokenService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
object RemoteServiceModule {
    @Provides
    fun provideAuthService(
        builder: Retrofit.Builder,
        converterFactory: Converter.Factory,
        @Named(NetworkModule.NAME_AUTH_CLIENT) client: OkHttpClient
    ): AuthService {
        return builder
            .baseUrl(BuildConfig.AUTH_BASE_URL)
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
            .create(AuthService::class.java)
    }

    @Provides
    fun provideApiService(
        builder: Retrofit.Builder,
        converterFactory: Converter.Factory,
        @Named(NetworkModule.NAME_API_CLIENT) client: OkHttpClient
    ): TokenService {
        return builder
            .baseUrl(BuildConfig.TOKEN_BASE_URL)
            .addConverterFactory(converterFactory)
            .client(client)
            .build()
            .create(TokenService::class.java)
    }
}
