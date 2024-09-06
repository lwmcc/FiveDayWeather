package com.mccarty.fivedayweather.domain.model

data class CityWeatherData(
    val name: String,
    val country: String?,
    val zip: String?,
    val sunrise: Long,
    val sunset: Long,
    val timezone: Int,
    val lat: Double? = 0.0,
    val lon: Double? = 0.0,
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
    val main: Main,
)