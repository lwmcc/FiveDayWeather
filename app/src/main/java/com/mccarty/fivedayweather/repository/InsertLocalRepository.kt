package com.mccarty.fivedayweather.repository

import com.mccarty.fivedayweather.domain.entity.FiveDayWeather

interface InsertLocalRepository {
    suspend fun insertFiveDayWeather(fiveDayWeather: FiveDayWeather)
}