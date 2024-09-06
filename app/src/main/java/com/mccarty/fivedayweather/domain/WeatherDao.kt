package com.mccarty.fivedayweather.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mccarty.fivedayweather.domain.entity.FiveDayWeather
import com.mccarty.fivedayweather.domain.model.CityWeatherData
import com.mccarty.fivedayweather.domain.model.ListItem

@Dao
interface WeatherDao {
    @Insert
    suspend fun insertFiveDayWeather(fiveDayWeather: FiveDayWeather)

    @Query("SELECT city_weather_data FROM city_weather")
    suspend fun getFiveDayWeather(): String
}