package com.batdemir.oauth2.models

import com.batdemir.oauth2.BuildConfig
import com.google.gson.annotations.SerializedName

data class AuthorizationRequest(
    @SerializedName("response_type")
    val responseType: String,
    @SerializedName("client_id")
    val clientId: String = BuildConfig.CLIENT_ID,
    @SerializedName("redirect_uri")
    val redirectUri: String?,
    val scope: String?,
    val state: String?
)
