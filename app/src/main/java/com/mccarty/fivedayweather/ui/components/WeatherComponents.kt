package com.mccarty.fivedayweather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mccarty.fivedayweather.R
import com.mccarty.fivedayweather.domain.model.CityTemp
import com.mccarty.fivedayweather.domain.model.CityWeatherData
import com.mccarty.fivedayweather.domain.model.ListItem
import com.mccarty.fivedayweather.navigation.AppNavigation
import com.mccarty.fivedayweather.navigation.DestinationKeys
import com.mccarty.fivedayweather.ui.MainViewModel
import com.mccarty.fivedayweather.ui.MainViewModel as FiveDayWeather


@Composable
fun MainScreen(
    weather: MainViewModel.FiveDayWeather,
    weatherDetails: WeatherDetails?,
    navController: NavHostController,
    padding: PaddingValues,
    onSubmit: (Int) -> Unit,
    onCardClick: (WeatherDetails) -> Unit,
    navActions: AppNavigation = remember(navController) {
        AppNavigation(navController)
    },
) {
    NavHost(navController = navController, startDestination = DestinationKeys.MAIN_SCREEN.name) {
        composable(DestinationKeys.MAIN_SCREEN.name) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onSurface)
                    .padding(top = padding.calculateTopPadding())
            ) {
                SearchBox(onClick = {
                    onSubmit(it)
                })

                when (weather) {
                    is FiveDayWeather.FiveDayWeather.Pending -> {
                        if (weather.pending) {
                            CircleSpinner()
                        }
                    }

                    is FiveDayWeather.FiveDayWeather.Error -> {
                        weather.cityWeatherDataDb?.let {
                            WeatherDataDb(weather = it, onCardClick = { weatherDetails ->
                                onCardClick(weatherDetails)
                                navActions.navigateToDetails()
                            })
                        }
                    }

                    is FiveDayWeather.FiveDayWeather.Success -> {
                        WeatherData(weather = weather.data, onCardClick = { weatherDetails ->
                            onCardClick(weatherDetails)
                            navActions.navigateToDetails()
                        })
                    }
                }
            }
        }
        composable(DestinationKeys.DETAILS_SCREEN.name) {
            WeatherDetailsComponents(weatherDetails)
        }
    }
}

@Composable
fun SearchBox(onClick: (Int) -> Unit) {
    val maxLength = 5
    val pattern = Regex("^\\d+\$")
    var searchText by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchText,
            onValueChange = { if (it.length <= maxLength && it.matches(pattern)) searchText = it },
            label = { Text(stringResource(id = R.string.zip_code)) },
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onClick(
                    if (searchText.trim().isEmpty()) {
                        0
                    } else {
                        searchText.toInt()
                    }
                )
            }
        ) {
            Text(stringResource(id = R.string.submit))
        }
    }
}

@Composable
fun WeatherData(weather: List<ListItem>, onCardClick: (WeatherDetails) -> Unit) {
    LazyColumn {
        items(weather) { weatherItem ->
            WeatherItem(weatherItem, onCardClick = {
                onCardClick(it)
            })
        }
    }
}

@Composable
fun WeatherDataDb(weather: CityWeatherData, onCardClick: (WeatherDetails) -> Unit) {
    LazyColumn {
        items(weather.cityTemps) { weatherItem ->
            WeatherItemDb(weatherItem, onCardClick = {
                onCardClick(it)
            })
        }
    }
}

@Composable
fun WeatherItem(weatherItem: ListItem, onCardClick: (WeatherDetails) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    onCardClick(
                        WeatherDetails(
                            temp = weatherItem.main.temp,
                            tempMin = weatherItem.main.tempMin,
                            temMax = weatherItem.main.tempMax,
                            description = weatherItem.weather.firstOrNull()?.description,
                        )
                    )
                }
            )
            .padding(5.dp)
            .shadow(
                elevation = 2.dp,
                shape = RectangleShape,
                clip = false,
                ambientColor = DefaultShadowColor,
                spotColor = DefaultShadowColor,
            ),
        shape = MaterialTheme.shapes.extraSmall,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                text = "${weatherItem.main.temp.toInt()}\u00B0 ",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "${stringResource(id = R.string.feels_like)} ${weatherItem.main.feelsLike.toInt()}\u00B0",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal,
            )
            
            Text(
                text = stringResource(id = R.string.humidity, weatherItem.main.humidity.toString()),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}

@Composable
fun WeatherItemDb(weatherItem: CityTemp, onCardClick: (WeatherDetails) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    onCardClick(
                        WeatherDetails(
                            temp = weatherItem.main.temp,
                            tempMin = weatherItem.main.tempMin,
                            temMax = weatherItem.main.tempMax,
                            description = weatherItem.weather.firstOrNull()?.description,
                        )
                    )
                }
            )
            .padding(5.dp)
            .shadow(
                elevation = 2.dp,
                shape = RectangleShape,
                clip = false,
                ambientColor = DefaultShadowColor,
                spotColor = DefaultShadowColor,
            ),
        shape = MaterialTheme.shapes.extraSmall,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                text = "${weatherItem.main.temp.toInt()}\u00B0",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "${stringResource(id = R.string.feels_like)} ${weatherItem.main.feelsLike.toInt()}\u00B0",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal,
            )

            Text(
                text = stringResource(id = R.string.humidity, weatherItem.main.humidity.toString()),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}

data class WeatherDetails(
    val temp: Double? = 0.0,
    val tempMin: Double? = 0.0,
    val temMax: Double? = 0.0,
    val description: String? = null,
)