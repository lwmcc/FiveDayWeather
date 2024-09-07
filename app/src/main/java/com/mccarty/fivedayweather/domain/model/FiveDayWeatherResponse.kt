package com.mccarty.fivedayweather.domain.model
data class WeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Visibility>,
    val message: Int
)

data class Visibility(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val rain: Rain,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

data class Rain(
    val `3h`: Double
)
