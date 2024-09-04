package com.mccarty.fivedayweather.domain

import com.mccarty.fivedayweather.Constants.APP_ID
import com.mccarty.fivedayweather.api.WeatherService
import com.mccarty.fivedayweather.domain.model.ApiResponse
import com.mccarty.fivedayweather.domain.network.NetworkRequest
import javax.inject.Inject

class FetchWeatherRepository @Inject constructor(private val weatherService: WeatherService): Repository {
    override suspend fun fetchWeather(zip: String): NetworkRequest<ApiResponse> {
        return weatherService.fetch5DayWeather(zip, APP_ID)
    }
}