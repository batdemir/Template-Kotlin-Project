package com.batdemir.oauth2.features.auth

import com.batdemir.oauth2.BuildConfig
import com.batdemir.oauth2.models.AuthorizationRequest
import com.batdemir.oauth2.models.AuthorizationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST("/authorize")
    suspend fun auth(@Body request: AuthorizationRequest): Response<AuthorizationResponse>

    @POST("/authorize")
    suspend fun auth(
        @Query("response_type") responseType: String,
        @Query("client_id") clientId: String = BuildConfig.CLIENT_ID,
        @Query("redirect_uri") redirectUri: String?,
        @Query("scope") scope: String?,
        @Query("state") state: String?
    ): Response<AuthorizationResponse>
}
