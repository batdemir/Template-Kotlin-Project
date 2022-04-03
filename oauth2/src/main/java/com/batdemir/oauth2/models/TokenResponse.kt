package com.batdemir.oauth2.models

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("access_token")
    val accessToken: String?,
    @SerializedName("token_type")
    val tokenType: String?,
    @SerializedName("expires_in")
    val expiresIn: String?,
    @SerializedName("refresh_token")
    val refreshToken: String?
)
