package com.mccarty.fivedayweather.domain

import com.mccarty.fivedayweather.domain.model.ApiResponse
import com.mccarty.fivedayweather.domain.network.NetworkRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchWeatherUseCase @Inject constructor(private val fetchWeatherRepository: FetchWeatherRepository) {
    suspend fun fetchFiveDayWeather(zip: String): Flow<NetworkRequest<ApiResponse>> = flow {
        emit(fetchWeatherRepository.fetchWeather(zip))
    }
}