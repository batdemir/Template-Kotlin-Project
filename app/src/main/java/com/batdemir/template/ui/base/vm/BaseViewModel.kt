package com.batdemir.template.ui.base.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.batdemir.template.data.entities.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseViewModel(
    val baseLiveData: MutableLiveData<State> = MutableLiveData()
) : ViewModel() {
    init {
        baseLiveData.value = State.Nothing
    }

    inline fun <T> Flow<Resource<T>>.sendRequest(
        requestType: RequestType = RequestType.ACTION,
        errorType: ErrorType = ErrorType.NOT_SHOW,
        crossinline onComplete: (T) -> Unit
    ) {
        onEach { response ->
            when (response) {
                is Resource.Loading -> baseLiveData.value = State.ShowLoading
                is Resource.Error -> {
                    when (errorType) {
                        ErrorType.NOT_SHOW -> baseLiveData.value = State.Error
                        ErrorType.SHOW -> baseLiveData.value =
                            State.ShowError(response.exception, requestType)
                    }
                }
                is Resource.Success -> {
                    onComplete(response.data)
                    baseLiveData.value = State.ShowContent
                }
            }
        }.launchIn(viewModelScope)
    }

    inline fun <T> Flow<Resource<T>>.sendRequest(
        requestType: RequestType = RequestType.ACTION,
        errorType: ErrorType = ErrorType.NOT_SHOW,
        crossinline onComplete: (T) -> Unit,
        crossinline onError: (Throwable) -> Unit
    ) {
        onEach { response ->
            when (response) {
                is Resource.Loading -> baseLiveData.value = State.ShowLoading
                is Resource.Error -> {
                    onError(response.exception)
                    when (errorType) {
                        ErrorType.NOT_SHOW -> baseLiveData.value = State.Error
                        ErrorType.SHOW -> baseLiveData.value =
                            State.ShowError(response.exception, requestType)
                    }
                }
                is Resource.Success -> {
                    onComplete(response.data)
                    baseLiveData.value = State.ShowContent
                }
            }
        }.launchIn(viewModelScope)
    }

    sealed class State {
        object Nothing : State()
        object Error : State()
        object ShowLoading : State()
        object ShowContent : State()
        data class ShowError(val throwable: Throwable, val requestType: RequestType) : State()
        data class ShowDialog(val message: String) : State()
    }

    enum class RequestType {
        INIT,
        ACTION
    }

    enum class ErrorType {
        SHOW,
        NOT_SHOW
    }
}