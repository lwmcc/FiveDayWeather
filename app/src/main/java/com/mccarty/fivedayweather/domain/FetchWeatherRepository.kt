package com.mccarty.fivedayweather.domain

import com.mccarty.fivedayweather.api.WeatherService
import com.mccarty.fivedayweather.domain.model.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FetchWeatherRepository @Inject constructor(private val weatherService: WeatherService): Repository {
    override suspend fun fetchWeather() {
        val response = weatherService.fetch5DayWeather()

        println("FetchWeatherRepository ***** ${response}.")
    }
}