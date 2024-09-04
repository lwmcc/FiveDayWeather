package com.mccarty.fivedayweather.api

import com.mccarty.fivedayweather.Constants.APP_ID_URL
import com.mccarty.fivedayweather.Constants.FORECAST_URL
import com.mccarty.fivedayweather.Constants.ZIP_URL
import com.mccarty.fivedayweather.domain.model.ApiResponse
import com.mccarty.fivedayweather.domain.network.NetworkRequest
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET(FORECAST_URL)
    suspend fun fetch5DayWeather(
        @Query(ZIP_URL) zip: String,
        @Query(APP_ID_URL) appid: String,
    ): NetworkRequest<ApiResponse>
}