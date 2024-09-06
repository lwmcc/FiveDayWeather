package com.mccarty.fivedayweather

import app.cash.turbine.test
import com.mccarty.fivedayweather.domain.FetchWeather
import com.mccarty.fivedayweather.domain.InsertWeather
import com.mccarty.fivedayweather.domain.entity.FiveDayWeather
import com.mccarty.fivedayweather.domain.model.ApiResponse
import com.mccarty.fivedayweather.domain.model.City
import com.mccarty.fivedayweather.domain.model.CityTemp
import com.mccarty.fivedayweather.domain.model.CityWeatherData
import com.mccarty.fivedayweather.domain.model.Clouds
import com.mccarty.fivedayweather.domain.model.Coord
import com.mccarty.fivedayweather.domain.model.ListItem
import com.mccarty.fivedayweather.domain.model.Location
import com.mccarty.fivedayweather.domain.model.Main
import com.mccarty.fivedayweather.domain.model.Sys
import com.mccarty.fivedayweather.domain.model.Weather
import com.mccarty.fivedayweather.domain.model.Wind
import com.mccarty.fivedayweather.domain.network.NetworkRequest
import com.mccarty.fivedayweather.ui.MainViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertIs

class MainViewModelTest {

    @JvmField
    @Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setup() {
        mainViewModel = MainViewModel(FetchWeatherUseCaseFake(), InsertWeatherUseCaseFake())
    }

    @Test
    fun `verify data is success object`() = runTest {
        mainViewModel.fetchFiveDayWeather(FAKE_ZIP)
        mainViewModel.weather.test {
            assertThat(awaitItem(), instanceOf(MainViewModel.FiveDayWeather.Success::class.java))
        }
    }

    @Test
    fun `assert weather data list has size of 1`() = runTest {
        mainViewModel.fetchFiveDayWeather(FAKE_ZIP)
        mainViewModel.weather.test {
            val weather = awaitItem()
            assertIs<MainViewModel.FiveDayWeather.Success>(weather)
            assertEquals(1, weather.data.size)
        }
    }

    @Test
    fun `assert main weather temp is correct `() = runTest {
        mainViewModel.fetchFiveDayWeather(FAKE_ZIP)
        mainViewModel.weather.test {
            val weather = awaitItem()
            assertIs<MainViewModel.FiveDayWeather.Success>(weather)
            assertEquals(294.93, weather.data[0].main.temp, DELTA)
        }
    }

    @Test
    fun `assert City name is correct`() = runTest {
        mainViewModel.fetchFiveDayWeather(FAKE_ZIP)
        val  cityWeatherData = FetchWeatherUseCaseFake().getCityWeatherData()
        assertEquals(FAKE_CITY, cityWeatherData.name)
    }

    private val coord = Coord(lat = 44.34, lon = 10.99)

    private val city = City(
        id = 3163858,
        name = "Zocca",
        coord = coord,
        country = "IT",
        population = 4593,
        timezone = 7200,
        sunrise = 1661834187,
        sunset = 1661882248,
    )

    private val weather = Weather(
        id = 500,
        main = "Rain",
        description = "light rain",
        icon = "10d",
    )

    private val main = Main(
        temp = 294.93,
        feelsLike = 294.83,
        tempMin = 294.93,
        tempMax = 294.93,
        pressure = 1018,
        seaLevel = 1018,
        grndLevel = 935,
        humidity = 64,
        tempKf = 333.0,
    )

    private val clouds = Clouds(all = 88)

    private val wind = Wind(speed = 1.14, deg = 17, gust = 1.57)

    private val sys = Sys(pod = "n")

    private val listItem = ListItem(
        dt = 0,
        main = main,
        weather = listOf(weather),
        clouds = clouds,
        wind = wind,
        visibility = 10000,
        pop = 0.0,
        sys = sys,
        dtTxt = "2022-09-04 12:00:00",
    )

    val apiResponse = ApiResponse(
        cod = "200",
        message = 0,
        cnt = 40,
        list = listOf(listItem),
        city = city,
    )

    val location = Location(
        country = "US",
        lat = 33.30616000,
        lon = -111.84125000,
        name = "Chandler",
        zip = "85248",
    )

    private val cityTemps = CityTemp(
        temp = 294.93,
        feelsLike = 294.83,
        tempKf = 333.0,
        gust = 15.5,
        speed = 55.5,
        humidity = 13,
        deg = 33,
        all = 88,
        visibility = 132,
        weather = listOf(weather),
        main = main,
    )

    val cityWeatherData = CityWeatherData(
        name = "Chandler",
        country = "US",
        zip = "85248",
        sunset = 1661882248,
        sunrise = 1661834187,
        timezone = 7200,
        lat = 33.30616000,
        lon = -111.84125000,
        cityTemps = listOf(cityTemps),
    )

    inner class FetchWeatherUseCaseFake : FetchWeather {
        override suspend fun fetchLocation(zip: String): Flow<NetworkRequest<Location>> {
            return flow {
                emit(NetworkRequest.Success(location))
            }
        }

        override suspend fun fetchFiveDayWeatherLatLon(
            lat: String,
            lon: String
        ): Flow<NetworkRequest<ApiResponse>> {
            return flow {
                emit(NetworkRequest.Success(apiResponse))
            }
        }

        override suspend fun getFiveDayWeatherLocal(): Flow<String> {
            // TODO: to implement
            return emptyFlow()
        }

        override suspend fun getCityWeatherData(): CityWeatherData = cityWeatherData
    }

    inner class InsertWeatherUseCaseFake : InsertWeather {
        override suspend fun insertFiveDayWeather(fiveDayWeather: FiveDayWeather) {
            // TODO: to implement
        }
    }

    companion object {
        const val FAKE_ZIP = "85248"
        const val FAKE_CITY = "Chandler"
        const val DELTA = 0.0001
    }
}