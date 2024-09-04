package com.mccarty.fivedayweather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mccarty.fivedayweather.domain.FetchWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val fetchWeatherUseCase: FetchWeatherUseCase): ViewModel() {

    fun fetchFiveDayWeather() {
        viewModelScope.launch {
            fetchWeatherUseCase.fetchFiveDayWeather()
        }
    }
}