package com.batdemir.template.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.batdemir.template.other.Resource
import kotlinx.coroutines.Dispatchers

//Local and remote
fun <T, A> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.throwable ?: Throwable("performGetOperation")))
            emitSource(source)
        }
    }

// Only remote
fun <T> performGetOperation(networkCall: suspend () -> Resource<T>):
        LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            emit(Resource.success(responseStatus.data!!))
        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.throwable ?: Throwable("performGetOperation")))
        }
    }

// Only local
fun <T> performGetOperation(databaseQuery: () -> LiveData<T>):
        LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)
    }
