package com.mccarty.fivedayweather.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mccarty.fivedayweather.domain.entity.FiveDayWeather

@Database(entities = [FiveDayWeather::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}