package com.mccarty.fivedayweather.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mccarty.fivedayweather.R

@Composable
fun WeatherDetailsComponents(weatherDetails: WeatherDetails?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSurface)
    ) {
        if (weatherDetails != null) {
            Row(
                modifier = Modifier.padding(24.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row {
                        Text(
                            text = stringResource(id = R.string.temp),
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.surface,
                        )

                        Text(
                            text = weatherDetails.temp?.toInt().toString(),
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.surface,
                        )
                    }
                }
                Column(modifier = Modifier.padding(12.dp)) {

                    Row {

                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = stringResource(id = R.string.max_temp),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.surface,
                        )

                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = weatherDetails.temMax?.toInt().toString(),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.surface,
                        )
                    }
                    Row {

                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = stringResource(id = R.string.min_temp),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.surface,
                        )

                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = weatherDetails.tempMin?.toInt().toString(),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.surface,
                        )
                    }

                    Column {

                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = stringResource(id = R.string.weather_conditions),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.surface,
                        )

                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = weatherDetails.description
                                ?: stringResource(id = R.string.weather_description),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.surface,
                        )
                    }
                }
            }
        } else {
            Column {
                Text(text = stringResource(id = R.string.screen_load_error))
            }
        }
    }
}