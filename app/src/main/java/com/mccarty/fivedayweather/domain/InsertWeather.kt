package com.mccarty.fivedayweather.domain

import com.mccarty.fivedayweather.domain.entity.FiveDayWeather

interface InsertWeather {
    suspend fun insertFiveDayWeather(fiveDayWeather: FiveDayWeather)
}