package com.mccarty.fivedayweather.api

import com.mccarty.fivedayweather.domain.model.ApiResponse
import com.mccarty.fivedayweather.domain.network.NetworkRequest
import retrofit2.http.GET

interface WeatherService {
    // 305ae17544e274e4569bcc6177f57dc7
    // https://api.openweathermap.org/data/2.5/forecast?zip=85248&appid=305ae17544e274e4569bcc6177f57dc7
    @GET("forecast?zip=85248&appid=305ae17544e274e4569bcc6177f57dc7")
    suspend fun fetch5DayWeather(): NetworkRequest<ApiResponse>
}