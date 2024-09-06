package com.mccarty.fivedayweather.domain

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mccarty.fivedayweather.domain.model.ApiResponse
import com.mccarty.fivedayweather.domain.model.City
import com.mccarty.fivedayweather.domain.model.CityWeatherData
import com.mccarty.fivedayweather.domain.model.Clouds
import com.mccarty.fivedayweather.domain.model.Coord
import com.mccarty.fivedayweather.domain.model.ListItem
import com.mccarty.fivedayweather.domain.model.Main
import com.mccarty.fivedayweather.domain.model.Sys
import com.mccarty.fivedayweather.domain.model.Weather
import com.mccarty.fivedayweather.domain.model.Wind

@ProvidedTypeConverter
class WeatherTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromCityWeatherData(cityWeatherData: CityWeatherData): String {
        return gson.toJson(cityWeatherData)
    }

    @TypeConverter
    fun toCityWeatherData(cityWeatherDataString: String): CityWeatherData {
        val type = object : TypeToken<CityWeatherData>() {}.type
        return gson.fromJson(cityWeatherDataString, type)
    }

/*
    @TypeConverter
    fun fromWeatherList(value: List<Weather>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toWeatherList(value: String?): List<Weather>? {
        val listType = object : TypeToken<List<Weather>>() {}.type
        return gson.fromJson(value, listType)
    }*/

  /*  @TypeConverter
    fun fromMain(value: Main?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toMain(value: String?): Main? {
        return gson.fromJson(value, Main::class.java)
    }

    @TypeConverter
    fun fromClouds(value: Clouds?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toClouds(value: String?): Clouds? {
        return gson.fromJson(value, Clouds::class.java)
    }

    @TypeConverter
    fun fromWind(value: Wind?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toWind(value: String?): Wind? {
        return gson.fromJson(value, Wind::class.java)
    }

    @TypeConverter
    fun fromSys(value: Sys?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toSys(value: String?): Sys? {
        return gson.fromJson(value, Sys::class.java)
    }

    @TypeConverter
    fun fromListItem(value: List<ListItem>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toListItem(value: String?): List<ListItem>? {
        val listType = object : TypeToken<List<ListItem>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromCoord(value: Coord?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCoord(value: String?): Coord? {
        return gson.fromJson(value, Coord::class.java)
    }

    @TypeConverter
    fun fromCity(value: City?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCity(value: String?): City? {
        return gson.fromJson(value, City::class.java)
    }

    @TypeConverter
    fun fromApiResponse(value: ApiResponse?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toApiResponse(value: String?): ApiResponse? {
        return gson.fromJson(value, ApiResponse::class.java)
    }*/
}