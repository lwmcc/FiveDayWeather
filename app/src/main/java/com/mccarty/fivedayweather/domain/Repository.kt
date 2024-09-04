package com.mccarty.fivedayweather.domain

interface Repository {
    suspend fun fetchWeather()
}