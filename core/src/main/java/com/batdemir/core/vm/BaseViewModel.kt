package com.batdemir.core.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batdemir.core.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseViewModel(
    val baseLiveData: MutableLiveData<State> = MutableLiveData()
) : ViewModel() {
    inline fun <T> Flow<Resource<T>>.handle(
        requestType: RequestType = RequestType.ACTION,
        errorType: ErrorType = ErrorType.NOT_SHOW,
        crossinline onComplete: (T) -> Unit,
    ) {
        handle(
            requestType,
            errorType,
            onComplete,
            onError = {}
        )
    }

    inline fun <T> Flow<Resource<T>>.handle(
        requestType: RequestType = RequestType.ACTION,
        errorType: ErrorType = ErrorType.NOT_SHOW,
        crossinline onComplete: (T) -> Unit,
        crossinline onError: (Throwable?) -> Unit,
    ) {
        onEach { response ->
            when (response.status) {
                Resource.Status.LOADING -> baseLiveData.value = State.ShowLoading(requestType)
                Resource.Status.ERROR -> {
                    onError(response.throwable)
                    when (errorType) {
                        ErrorType.NOT_SHOW -> baseLiveData.value = State.Error
                        ErrorType.SHOW -> baseLiveData.value =
                            State.ShowError(requestType, response.throwable)
                    }
                }
                Resource.Status.SUCCESS -> {
                    onComplete(response.data!!)
                    baseLiveData.value = State.ShowContent(requestType)
                }
            }
        }.launchIn(viewModelScope)
    }

    sealed class State {
        object Empty : State()
        object Error : State()
        data class ShowContent(val requestType: RequestType = RequestType.INIT) : State()
        data class ShowLoading(val requestType: RequestType = RequestType.INIT) : State()
        data class ShowError(
            val requestType: RequestType = RequestType.INIT,
            val throwable: Throwable?
        ) : State()

        data class ShowDialog(val message: String) : State()
    }

    enum class RequestType {
        INIT,
        ACTION,
        CUSTOM
    }

    enum class ErrorType {
        SHOW,
        NOT_SHOW
    }
}