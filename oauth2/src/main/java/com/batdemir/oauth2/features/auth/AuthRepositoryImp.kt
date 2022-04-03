package com.batdemir.oauth2.features.auth

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.liveData
import com.batdemir.core.manager.resource.MyResourceManager
import com.batdemir.core.models.Resource
import com.batdemir.oauth2.BuildConfig
import com.batdemir.oauth2.R
import com.batdemir.oauth2.models.AuthorizationRequest
import com.batdemir.oauth2.models.AuthorizationResponse
import kotlinx.coroutines.Dispatchers
import java.util.UUID
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
    myResourceManager: MyResourceManager
) : AuthRepository {
    private val request = AuthorizationRequest(
        responseType = myResourceManager.getResources().getString(R.string.AUTH_RESPONSE_TYPE),
        redirectUri = myResourceManager.getResources().getString(R.string.AUTH_REDIRECT_URI),
        scope = myResourceManager.getResources().getString(R.string.AUTH_SCOPE),
        state = UUID.randomUUID().toString(),
    )

    override fun auth(): LiveData<Resource<AuthorizationResponse>> = liveData(Dispatchers.IO) {
        handle { remoteDataSource.auth(request) }
    }

    override fun authQuery(): LiveData<Resource<AuthorizationResponse>> = liveData(Dispatchers.IO) {
        handle { remoteDataSource.authQuery(request) }
    }

    override fun createAuthUrl(): String {
        val uriBuilder = Uri.Builder()
        uriBuilder.scheme("https")
            .authority(BuildConfig.AUTH_BASE_URL)
            .appendPath("authorize")
            .appendQueryParameter("client_id", request.clientId)
            .appendQueryParameter("scope", request.scope)
            .appendQueryParameter("redirect_uri", request.redirectUri)
            .appendQueryParameter("state", request.state)
            .appendQueryParameter("response_type", request.responseType)
            .build()
        return uriBuilder.toString()
    }

    private suspend fun LiveDataScope<Resource<AuthorizationResponse>>.handle(call: suspend () -> Resource<AuthorizationResponse>) {
        emit(Resource.loading())
        val responseStatus = call.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            responseStatus.data?.let {
                emit(Resource.success(it))
            } ?: emit(Resource.error(Throwable("auth")))
        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.throwable ?: Throwable("auth")))
        }
    }
}
