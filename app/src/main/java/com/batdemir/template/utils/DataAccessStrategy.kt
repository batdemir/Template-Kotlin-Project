package com.batdemir.template.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.batdemir.template.data.entities.ResourceModel
import kotlinx.coroutines.Dispatchers

//Local and remote
fun <T, A> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> ResourceModel<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<ResourceModel<T>> =
    liveData(Dispatchers.IO) {
        emit(ResourceModel.loading())
        val source = databaseQuery.invoke().map { ResourceModel.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == ResourceModel.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == ResourceModel.Status.ERROR) {
            emit(ResourceModel.error(responseStatus.message!!))
            emitSource(source)
        }
    }

// Only remote
fun <T> performGetOperation(networkCall: suspend () -> ResourceModel<T>):
        LiveData<ResourceModel<T>> =
    liveData(Dispatchers.IO) {
        emit(ResourceModel.loading())
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == ResourceModel.Status.SUCCESS) {
            emit(ResourceModel.success(responseStatus.data!!))
        } else if (responseStatus.status == ResourceModel.Status.ERROR) {
            emit(ResourceModel.error(responseStatus.message!!))
        }
    }

// Only local
fun <T> performGetOperation(databaseQuery: () -> LiveData<T>):
        LiveData<ResourceModel<T>> =
    liveData(Dispatchers.IO) {
        emit(ResourceModel.loading())
        val source = databaseQuery.invoke().map { ResourceModel.success(it) }
        emitSource(source)
    }
