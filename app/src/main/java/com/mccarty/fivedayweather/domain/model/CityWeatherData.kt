package com.mccarty.fivedayweather.domain.model

import androidx.room.Entity

data class CityWeatherData(
    val name: String,
    val sunrise: Long,
    val sunset: Long,
    val timezone: Int,
    val cityTemps: List<CityTemp>,
)

data class CityTemp(
    val temp: Double,
    val feelsLike: Double,
    val tempKf: Double,
    val gust: Double,
    val speed: Double,
    val humidity: Int,
    val deg: Int,
    val all: Int,
    val visibility: Int,
    val weather: List<Weather>,
)