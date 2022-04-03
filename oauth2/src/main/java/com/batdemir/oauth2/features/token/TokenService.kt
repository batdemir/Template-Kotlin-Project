package com.batdemir.oauth2.features.token

import com.batdemir.oauth2.models.TokenRequest
import com.batdemir.oauth2.models.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenService {
    @POST("/oauth/token")
    suspend fun getToken(@Body request: TokenRequest): Response<TokenResponse>
}
