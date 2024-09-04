package com.mccarty.fivedayweather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mccarty.fivedayweather.domain.FetchWeather
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
class MainViewModel @Inject constructor(private val fetchWeatherUseCase: FetchWeather): ViewModel() {

    sealed class FiveDayWeather {
        data class Pending(val pending: Boolean): FiveDayWeather()
        data class Error(val message: String): FiveDayWeather()
        data class Success(val data: List<ListItem>): FiveDayWeather()
    }

    private var _weather = MutableStateFlow<FiveDayWeather>(FiveDayWeather.Pending(false))
    var weather = _weather

    fun fetchFiveDayWeather(zip: String) {
        _weather.value = FiveDayWeather.Pending(true)
        viewModelScope.launch {

            val location: Deferred<Location?> = async {
                when (val loc = fetchWeatherUseCase.fetchLocation(zip).catch {
                    it.message // TODO: Log this
                }.first()) {
                    is NetworkRequest.Error -> {
                        null
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
                            _weather.value = FiveDayWeather.Error(request.message)
                        }

                        is NetworkRequest.Success -> {
                            _weather.value = FiveDayWeather.Success(request.data.list)
                        }
                    }
                }
        }
    }
}