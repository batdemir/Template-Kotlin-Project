package com.batdemir.oauth2.features.token

import com.batdemir.core.data.remote.BaseDataSource
import com.batdemir.oauth2.models.TokenRequest
import javax.inject.Inject

class TokenRemoteDataSource @Inject constructor(
    private val service: TokenService
) : BaseDataSource() {
    suspend fun getToken(request: TokenRequest) = getResult { service.getToken(request) }
}
