package com.mccarty.fivedayweather.domain

import com.mccarty.fivedayweather.domain.model.ApiResponse
import com.mccarty.fivedayweather.domain.model.Location
import com.mccarty.fivedayweather.domain.network.NetworkRequest

interface Repository {
    suspend fun fetchWeatherLatLon(lat: String, lon: String): NetworkRequest<ApiResponse>
    suspend fun fetchLocation(zip: String): NetworkRequest<Location>
}