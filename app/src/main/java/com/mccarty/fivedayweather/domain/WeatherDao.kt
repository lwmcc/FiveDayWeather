package com.mccarty.fivedayweather.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mccarty.fivedayweather.domain.entity.FiveDayWeather

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFiveDayWeather(fiveDayWeather: FiveDayWeather)

    @Query("SELECT city_weather_data FROM city_weather")
    suspend fun getFiveDayWeather(): String
}