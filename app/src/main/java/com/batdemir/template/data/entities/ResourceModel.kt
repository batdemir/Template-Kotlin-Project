package com.batdemir.template.data.entities

data class ResourceModel<out T>(val status: Status, val data: T?, val message: String?) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): ResourceModel<T> {
            return ResourceModel(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): ResourceModel<T> {
            return ResourceModel(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): ResourceModel<T> {
            return ResourceModel(Status.LOADING, data, null)
        }
    }
}