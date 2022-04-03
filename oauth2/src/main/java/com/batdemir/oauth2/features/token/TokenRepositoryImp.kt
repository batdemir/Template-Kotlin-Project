package com.batdemir.oauth2.features.token

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.batdemir.core.data.remote.interceptor.AuthInterceptor
import com.batdemir.core.manager.resource.MyResourceManager
import com.batdemir.core.manager.storage.MyStorageManager
import com.batdemir.core.models.Resource
import com.batdemir.oauth2.R
import com.batdemir.oauth2.models.TokenRequest
import com.batdemir.oauth2.models.TokenResponse
import com.batdemir.oauth2.other.Constant
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class TokenRepositoryImp @Inject constructor(
    private val remoteDataSource: TokenRemoteDataSource,
    private val myStorageManager: MyStorageManager,
    private val myResourceManager: MyResourceManager
) : TokenRepository {
    private val key = myResourceManager.getResources().getString(R.string.KEY_REFRESH_TOKEN)
    private val grantType = myResourceManager.getResources().getString(R.string.TOKEN_GRANT_TYPE)
    override fun getTokenRequest(code: String): TokenRequest = TokenRequest(
        code = code,
        grantType = grantType,
        redirectUri = myResourceManager.getResources().getString(R.string.AUTH_REDIRECT_URI)
    )

    override fun getRefreshTokenRequest(): TokenRequest = TokenRequest(
        grantType = grantType,
        refreshToken = myStorageManager.getString(key)
    )

    override fun getToken(request: TokenRequest): LiveData<Resource<TokenResponse>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val responseStatus = remoteDataSource.getToken(request)
        if (responseStatus.status == Resource.Status.SUCCESS) {
            responseStatus.data?.let {
                myStorageManager.setString(AuthInterceptor.KEY_TOKEN_TYPE, it.tokenType ?: Constant.STRING_EMPTY)
                myStorageManager.setString(AuthInterceptor.KEY_TOKEN, it.accessToken ?: Constant.STRING_EMPTY)
                myStorageManager.setString(key, it.refreshToken ?: Constant.STRING_EMPTY)
                emit(Resource.success(it))
            }
        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.throwable ?: Throwable("getToken")))
        }
    }
}
