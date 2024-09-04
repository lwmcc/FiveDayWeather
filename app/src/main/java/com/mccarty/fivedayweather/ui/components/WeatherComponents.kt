package com.mccarty.fivedayweather.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mccarty.fivedayweather.R
import com.mccarty.fivedayweather.domain.model.ListItem
import com.mccarty.fivedayweather.ui.MainViewModel


@Composable
fun MainScreen(text: String, weather: MainViewModel.FiveDayWeather, onClick: (String) -> Unit) {
    Column {
        SearchBox(text, onClick = {
            onClick(it)
        })

        when (weather) {
            is MainViewModel.FiveDayWeather.Pending -> {
                if (weather.pending) {
                    CircleSpinner()
                }
            }

            is MainViewModel.FiveDayWeather.Error -> {
                Error(stringResource(id = R.string.screen_load_error))
            }

            is MainViewModel.FiveDayWeather.Success -> {
                WeatherData(weather = weather.data)
            }
        }
    }
}

@Composable
fun SearchBox(text: String, onClick: (String) -> Unit) {
    var searchText by remember { mutableStateOf("Larry") }

        Row {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("McCarty") }
            )
            Button(onClick = {
                onClick("85248")
            }
            ) {

            }
        }
}

@Composable
fun WeatherData(weather: List<ListItem>) {
    LazyColumn {

        items(weather) { weatherItem ->
            WeatherItem(weatherItem)
        }
    }
}

@Composable
fun WeatherItem(weatherItem: ListItem) {

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
        text = weatherItem.main.temp.toString(),
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