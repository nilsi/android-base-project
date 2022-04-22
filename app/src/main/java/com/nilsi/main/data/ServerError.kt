package com.main.data

sealed class ServerError {
    object ConnectException : ServerError()
    object NotFoundException : ServerError()
    object BadCredentials : ServerError()
    data class Failure(val message: String? = null) : ServerError()
}