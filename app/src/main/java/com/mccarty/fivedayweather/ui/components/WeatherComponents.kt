package com.mccarty.fivedayweather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mccarty.fivedayweather.R
import com.mccarty.fivedayweather.domain.model.CityTemp
import com.mccarty.fivedayweather.domain.model.CityWeatherData
import com.mccarty.fivedayweather.domain.model.ListItem
import com.mccarty.fivedayweather.ui.MainViewModel as FiveDayWeather


@Composable
fun MainScreen(weather: FiveDayWeather.FiveDayWeather, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSurface)
    ) {
        SearchBox(onClick = {
            onClick(it)
        })

        when (weather) {
            is FiveDayWeather.FiveDayWeather.Pending -> {
                if (weather.pending) {
                    CircleSpinner()
                }
            }

            is FiveDayWeather.FiveDayWeather.Error -> {
                WeatherDataDb(weather = weather.cityWeatherDataDb, onCardClick = {
                    // TODO: go to next screen
                })
            }

            is FiveDayWeather.FiveDayWeather.Success -> {
                WeatherData(weather = weather.data, onCardClick = {
                    // TODO: go to next screen
                })
            }
        }
    }
}

@Composable
fun SearchBox(onClick: (String) -> Unit) {
    var searchText by rememberSaveable { mutableStateOf("") }

        Row {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text(stringResource(id = R.string.zip_code)) },
            )
            Button(onClick = {
                onClick(searchText)
            }
            ) {
                Text(stringResource(id = R.string.submit))
            }
        }
}

@Composable
fun WeatherData(weather: List<ListItem>, onCardClick: () -> Unit) {
    LazyColumn {
        items(weather) { weatherItem ->
            WeatherItem(weatherItem, onCardClick = {
                onCardClick()
            })
        }
    }
}

@Composable
fun WeatherDataDb(weather: CityWeatherData, onCardClick: () -> Unit) {
    LazyColumn {
        items(weather.cityTemps) { weatherItem ->
            WeatherItemDb(weatherItem, onCardClick = {
                onCardClick()
            })
        }
    }
}

@Composable
fun WeatherItem(weatherItem: ListItem, onCardClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    onCardClick()
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
        Text(
            text = weatherItem.main.temp.toString(),
            modifier = Modifier
                .paddingFromBaseline(top = 25.dp)
                .fillMaxWidth()
                .padding(start = 16.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = weatherItem.main.feelsLike.toString(),
            modifier = Modifier
                .paddingFromBaseline(top = 25.dp)
                .fillMaxWidth()
                .padding(start = 16.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = weatherItem.wind.speed.toString(),
            modifier = Modifier
                .paddingFromBaseline(top = 25.dp)
                .fillMaxWidth()
                .padding(start = 16.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        weatherItem.weather.forEach {
            Text(
                text = it.description,
                modifier = Modifier
                    .paddingFromBaseline(top = 25.dp)
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun WeatherItemDb(weatherItem: CityTemp, onCardClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    onCardClick()
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
        Text(
            text = weatherItem.temp.toString(),
            modifier = Modifier
                .paddingFromBaseline(top = 25.dp)
                .fillMaxWidth()
                .padding(start = 16.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = weatherItem.feelsLike.toString(),
            modifier = Modifier
                .paddingFromBaseline(top = 25.dp)
                .fillMaxWidth()
                .padding(start = 16.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Text(
            text = weatherItem.speed.toString(),
            modifier = Modifier
                .paddingFromBaseline(top = 25.dp)
                .fillMaxWidth()
                .padding(start = 16.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}