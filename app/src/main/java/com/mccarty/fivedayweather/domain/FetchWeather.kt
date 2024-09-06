package com.mccarty.fivedayweather.domain

import com.mccarty.fivedayweather.domain.model.ApiResponse
import com.mccarty.fivedayweather.domain.model.Location
import com.mccarty.fivedayweather.domain.network.NetworkRequest
import kotlinx.coroutines.flow.Flow

interface FetchWeather {
    suspend fun fetchLocation(zip: String): Flow<NetworkRequest<Location>>
    suspend fun fetchFiveDayWeatherLatLon(lat: String, lon: String):  Flow<NetworkRequest<ApiResponse>>
    suspend fun getFiveDayWeatherLocal(): Flow<String>
}