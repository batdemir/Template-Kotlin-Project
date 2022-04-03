package com.batdemir.oauth2.models

import com.batdemir.oauth2.BuildConfig
import com.google.gson.annotations.SerializedName

data class TokenRequest(
    @SerializedName("client_id")
    val clientId: String = BuildConfig.CLIENT_ID,
    @SerializedName("client_secret")
    val clientSecret: String = BuildConfig.SECRET_KEY,
    @SerializedName("grant_type")
    val grantType: String,
    val code: String? = null,
    @SerializedName("redirect_uri")
    val redirectUri: String? = null,
    @SerializedName("refresh_token")
    val refreshToken: String? = null
)
