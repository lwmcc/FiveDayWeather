package com.mccarty.fivedayweather.domain.network

sealed class NetworkRequest<T> {
    data class Error<T>(val message: String): NetworkRequest<T>()

    data class Success<T>(val data: T): NetworkRequest<T>()
}