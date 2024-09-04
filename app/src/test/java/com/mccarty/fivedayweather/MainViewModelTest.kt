package com.mccarty.fivedayweather

import com.mccarty.fivedayweather.domain.FetchWeather
import com.mccarty.fivedayweather.domain.Repository
import com.mccarty.fivedayweather.domain.model.ApiResponse
import com.mccarty.fivedayweather.domain.model.Location
import com.mccarty.fivedayweather.domain.network.NetworkRequest
import com.mccarty.fivedayweather.ui.MainViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @JvmField
    @Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setup() {
        mainViewModel = MainViewModel(FetchWeatherUseCaseFake())
    }

    @Test
    fun `verify weather data`() = runTest {
        mainViewModel.fetchFiveDayWeather("85248")
    }

/*    class FetchWeatherRepositoryFake: Repository {
        override suspend fun fetchWeatherLatLon(
            lat: String,
            lon: String
        ): NetworkRequest<ApiResponse> {

        }

        override suspend fun fetchLocation(zip: String): NetworkRequest<Location> {

        }
    }*/

    class FetchWeatherUseCaseFake: FetchWeather {
        val location = Location(
            country = "US",
            lat = 33.30616000,
            lon = -111.84125000,
            name = "Chandler",
            zip = "85248",
        )

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
                //emit(NetworkRequest.Success(""))
                // TODO: add response
            }
        }
    }
}