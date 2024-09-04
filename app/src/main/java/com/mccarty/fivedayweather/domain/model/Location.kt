package com.mccarty.fivedayweather.domain.model

data class Location(
    val country: String?,
    val lat: Double,
    val lon: Double,
    val name: String,
    val zip: String
)