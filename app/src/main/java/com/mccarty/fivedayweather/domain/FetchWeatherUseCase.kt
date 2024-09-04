package com.mccarty.fivedayweather.domain

import dagger.hilt.InstallIn
import javax.inject.Inject

class FetchWeatherUseCase @Inject constructor(private val fetchWeatherRepository: FetchWeatherRepository) {
    suspend fun fetchFiveDayWeather() {
        fetchWeatherRepository.fetchWeather()
    }
}