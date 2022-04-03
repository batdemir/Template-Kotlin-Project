package com.batdemir.oauth2.models

data class AuthorizationResponse(
    val code: String,
    val state: String?
)
