package com.mccarty.fivedayweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.mccarty.fivedayweather.ui.MainViewModel
import com.mccarty.fivedayweather.ui.components.MainScreen
import com.mccarty.fivedayweather.ui.theme.FiveDayWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FiveDayWeatherTheme {
                Scaffold { padding ->
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val weather = mainViewModel.weather.collectAsState().value
                        MainScreen(
                            "init box",
                            weather,
                        ) { zip -> mainViewModel.fetchFiveDayWeather(zip) }
                    }
                }
            }
        }
    }
}