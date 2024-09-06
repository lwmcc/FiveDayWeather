package com.mccarty.fivedayweather.repository

interface GetLocalRepository {
    suspend fun getFiveDayWeather(): String
}