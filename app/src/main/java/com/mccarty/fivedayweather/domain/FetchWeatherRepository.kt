package com.mccarty.fivedayweather.domain

import com.mccarty.fivedayweather.Constants.APP_ID
import com.mccarty.fivedayweather.Constants.FORECAST_URL
import com.mccarty.fivedayweather.Constants.ZIP_URL
import com.mccarty.fivedayweather.api.WeatherService
import com.mccarty.fivedayweather.domain.model.ApiResponse
import com.mccarty.fivedayweather.domain.model.Location
import com.mccarty.fivedayweather.domain.network.NetworkRequest
import javax.inject.Inject
import javax.inject.Named

class FetchWeatherRepository @Inject constructor(
    @Named(FORECAST_URL) private val weatherServiceForecast: WeatherService,
    @Named(ZIP_URL) private val weatherServiceZip: WeatherService,
) : Repository {
    override suspend fun fetchWeatherLatLon(lat: String, lon: String): NetworkRequest<ApiResponse> {
        return weatherServiceForecast.fetch5DayWeatherLatLon(lat, lon, APP_ID)
    }

    override suspend fun fetchLocation(zip: String): NetworkRequest<Location> {
        return weatherServiceZip.fetchLocationWithZip(zip, APP_ID)
    }
}