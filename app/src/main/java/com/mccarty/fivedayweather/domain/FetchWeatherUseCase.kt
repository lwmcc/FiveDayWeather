package com.mccarty.fivedayweather.domain

import com.mccarty.fivedayweather.domain.model.ApiResponse
import com.mccarty.fivedayweather.domain.model.Location
import com.mccarty.fivedayweather.domain.network.NetworkRequest
import com.mccarty.fivedayweather.repository.GetLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchWeatherUseCase @Inject constructor(
    private val fetchWeatherRepository: Repository,
    //private val getWeatherRepository: GetLocalRepository,
): FetchWeather {

    override suspend fun fetchLocation(zip: String): Flow<NetworkRequest<Location>> = flow {
        emit(fetchWeatherRepository.fetchLocation(zip))
    }

    override suspend fun fetchFiveDayWeatherLatLon(lat: String, lon: String): Flow<NetworkRequest<ApiResponse>> = flow {
        emit(fetchWeatherRepository.fetchWeatherLatLon(lat, lon))
    }

   /* override suspend fun getFiveDayWeatherLocal() {
       // getWeatherRepository.getFiveDayWeather()
    }*/
}