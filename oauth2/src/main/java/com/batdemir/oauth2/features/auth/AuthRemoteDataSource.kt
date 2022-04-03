package com.batdemir.oauth2.features.auth

import com.batdemir.core.data.remote.BaseDataSource
import com.batdemir.oauth2.models.AuthorizationRequest
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val service: AuthService
) : BaseDataSource() {
    suspend fun auth(request: AuthorizationRequest) = getResult { service.auth(request) }
    suspend fun authQuery(request: AuthorizationRequest) = getResult {
        service.auth(
            responseType = request.responseType,
            redirectUri = request.redirectUri,
            scope = request.scope,
            state = request.state
        )
    }
}
