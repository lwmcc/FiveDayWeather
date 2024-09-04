package com.mccarty.fivedayweather.domain

import com.mccarty.fivedayweather.domain.model.ApiResponse
import com.mccarty.fivedayweather.domain.network.NetworkRequest

interface Repository {
    suspend fun fetchWeather(zip: String): NetworkRequest<ApiResponse>
}