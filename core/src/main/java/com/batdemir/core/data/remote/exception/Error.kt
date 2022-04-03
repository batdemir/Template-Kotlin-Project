package com.batdemir.core.data.remote.exception

data class Error(
    override val message: String? = null
) : Throwable() {
    override fun toString(): String {
        return message ?: ""
    }
}
