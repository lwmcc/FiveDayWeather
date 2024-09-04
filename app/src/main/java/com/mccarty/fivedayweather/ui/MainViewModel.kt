package com.mccarty.fivedayweather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mccarty.fivedayweather.domain.FetchWeatherUseCase
import com.mccarty.fivedayweather.domain.model.ListItem
import com.mccarty.fivedayweather.domain.network.NetworkRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val fetchWeatherUseCase: FetchWeatherUseCase): ViewModel() {

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
            fetchWeatherUseCase.fetchFiveDayWeather(zip).collect { request ->
                when(request) {
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