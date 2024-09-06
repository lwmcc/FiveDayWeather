package com.mccarty.fivedayweather.domain

import com.mccarty.fivedayweather.domain.entity.FiveDayWeather
import com.mccarty.fivedayweather.repository.InsertLocalRepository
import javax.inject.Inject

class InsertWeatherUseCas @Inject constructor(private val insertLocalRepository: InsertLocalRepository): InsertWeather {
    override suspend fun insertFiveDayWeather(fiveDayWeather: FiveDayWeather) {
        insertLocalRepository.insertFiveDayWeather(fiveDayWeather)
    }
}