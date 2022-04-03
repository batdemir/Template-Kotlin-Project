package com.batdemir.oauth2.features.auth

import androidx.lifecycle.LiveData
import com.batdemir.core.models.Resource
import com.batdemir.oauth2.models.AuthorizationResponse

interface AuthRepository {
    fun auth(): LiveData<Resource<AuthorizationResponse>>
    fun authQuery(): LiveData<Resource<AuthorizationResponse>>
    fun createAuthUrl(): String
}
