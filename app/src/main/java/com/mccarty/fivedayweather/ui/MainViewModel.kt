package com.mccarty.fivedayweather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mccarty.fivedayweather.domain.FetchWeather
import com.mccarty.fivedayweather.domain.InsertWeather
import com.mccarty.fivedayweather.domain.model.CityTemp
import com.mccarty.fivedayweather.domain.model.CityWeatherData
import com.mccarty.fivedayweather.domain.model.ListItem
import com.mccarty.fivedayweather.domain.model.Location
import com.mccarty.fivedayweather.domain.network.NetworkRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchWeatherUseCase: FetchWeather,
    private val insertWeatherUseCas: InsertWeather,
) : ViewModel() {

    sealed class FiveDayWeather {
        data class Pending(val pending: Boolean): FiveDayWeather()
        data class Error(val cityWeatherDataDb: CityWeatherData): FiveDayWeather()
        data class Success(val data: List<ListItem>): FiveDayWeather()
    }

    private var _weather = MutableStateFlow<FiveDayWeather>(FiveDayWeather.Pending(false))
    var weather = _weather

    fun fetchFiveDayWeather(zip: Int) {
        _weather.value = FiveDayWeather.Pending(true)
        viewModelScope.launch {
            val cityWeatherData = fetchWeatherUseCase.getCityWeatherData()

            val location: Deferred<Location?> = async {
                when (val loc = fetchWeatherUseCase.fetchLocation(zip).catch {
                    it.message // TODO: Log this
                }.first()) {
                    is NetworkRequest.Error -> {
                        Location(
                            country = cityWeatherData.country,
                            lat = cityWeatherData.lat ?: 0.0,
                            lon = cityWeatherData.lon ?: 0.0,
                            name = cityWeatherData.name,
                            zip = cityWeatherData.zip,
                        )
                    }
                    is NetworkRequest.Success -> {
                        Location(
                            country = loc.data.country,
                            lat = loc.data.lat,
                            lon = loc.data.lon,
                            name = loc.data.name,
                            zip = loc.data.zip,
                        )
                    }
                }
            }

            val loc = location.await()
            fetchWeatherUseCase.fetchFiveDayWeatherLatLon(loc?.lat.toString(), loc?.lon.toString())
                .catch {
                    it.message // TODO: Log this
                }
                .collect { request ->
                    when (request) {
                        is NetworkRequest.Error -> {
                            _weather.value = FiveDayWeather.Error(cityWeatherData)
                        }

                        is NetworkRequest.Success -> {
                            val items = request.data.list
                            val cityTemps = items.map { item ->
                                CityTemp(
                                    temp = item.main.temp,
                                    feelsLike = item.main.feelsLike,
                                    humidity = item.main.humidity,
                                    tempKf = item.main.tempKf,
                                    deg = item.wind.deg,
                                    gust = item.wind.gust,
                                    speed = item.wind.speed,
                                    all = item.clouds.all,
                                    visibility = item.visibility,
                                    weather = item.weather,
                                    main = item.main,
                                )
                            }

                            val cityData = CityWeatherData(
                                name = request.data.city.name,
                                country = loc?.country,
                                zip = loc?.zip,
                                sunrise = request.data.city.sunrise,
                                sunset = request.data.city.sunset,
                                timezone = request.data.city.timezone,
                                lat = loc?.lat,
                                lon = loc?.lon,
                                cityTemps = cityTemps,
                            )

                            val  entity = com.mccarty.fivedayweather.domain.entity.FiveDayWeather(cityWeatherData = Gson().toJson(cityData))

                            _weather.value = FiveDayWeather.Success(items)
                            viewModelScope.launch {
                                insertWeatherUseCas.insertFiveDayWeather(entity)
                            }
                        }
                    }
                }
        }
    }
}