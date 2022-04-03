package com.batdemir.oauth2.features.token

import androidx.lifecycle.LiveData
import com.batdemir.core.models.Resource
import com.batdemir.oauth2.models.TokenRequest
import com.batdemir.oauth2.models.TokenResponse

interface TokenRepository {
    fun getTokenRequest(code: String): TokenRequest
    fun getRefreshTokenRequest(): TokenRequest
    fun getToken(request: TokenRequest): LiveData<Resource<TokenResponse>>
}
