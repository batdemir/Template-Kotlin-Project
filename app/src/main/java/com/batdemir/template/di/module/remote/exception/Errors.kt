package com.batdemir.template.di.module.remote.exception

class Errors {
    var errors: ArrayList<Error>? = null
}

data class Error(
    val description: String? = null,
    val customError: String? = null,
    val type: String? = null,
    val error: Boolean? = null,
    val errorCode: String? = null,
    var code: Int? = 0,
    val errors: ArrayList<Message>? = null,
    val data: Any? = null,
    override val message: String? = null
) : Throwable()

class Message {
    var type: String? = null
    var reason: String? = null
    var message: String? = null
    var subject: String? = null

    override fun toString(): String {
        return message ?: ""
    }
}