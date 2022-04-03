package com.batdemir.oauth2.di.remote

import com.batdemir.oauth2.features.auth.AuthRemoteDataSource
import com.batdemir.oauth2.features.auth.AuthService
import com.batdemir.oauth2.features.token.TokenRemoteDataSource
import com.batdemir.oauth2.features.token.TokenService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RemoteDataSourceModule {
    @Provides
    fun provideRemoteDataSourceAuth(service: AuthService) = AuthRemoteDataSource(service)

    @Provides
    fun provideRemoteDataSourceApi(service: TokenService) = TokenRemoteDataSource(service)
}
