package com.batdemir.oauth2.di

import com.batdemir.core.manager.resource.MyResourceManager
import com.batdemir.core.manager.storage.MyStorageManager
import com.batdemir.oauth2.features.auth.AuthRemoteDataSource
import com.batdemir.oauth2.features.auth.AuthRepository
import com.batdemir.oauth2.features.auth.AuthRepositoryImp
import com.batdemir.oauth2.features.token.TokenRemoteDataSource
import com.batdemir.oauth2.features.token.TokenRepository
import com.batdemir.oauth2.features.token.TokenRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    fun provideRepositoryAuth(
        remoteDataSource: AuthRemoteDataSource,
        myResourceManager: MyResourceManager
    ): AuthRepository = AuthRepositoryImp(remoteDataSource, myResourceManager)

    @Provides
    fun provideRepositoryApi(
        remoteDataSource: TokenRemoteDataSource,
        myStorageManager: MyStorageManager,
        myResourceManager: MyResourceManager
    ): TokenRepository = TokenRepositoryImp(remoteDataSource, myStorageManager, myResourceManager)
}
