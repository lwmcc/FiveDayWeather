package com.mccarty.fivedayweather.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_weather")
data class FiveDayWeather(
    @PrimaryKey var id: Int? = 0,
    @ColumnInfo(name = "city_weather_data") val cityWeatherData: String,
)