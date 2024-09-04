package com.mccarty.fivedayweather.module

import com.mccarty.fivedayweather.Constants.BASE_URL
import com.mccarty.fivedayweather.Constants.FORECAST_URL
import com.mccarty.fivedayweather.Constants.ZIP_BASE_URL
import com.mccarty.fivedayweather.Constants.ZIP_URL
import com.mccarty.fivedayweather.api.WeatherService
import com.mccarty.fivedayweather.domain.network.NetworkRequestAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Named(FORECAST_URL)
    @Singleton
    @Provides
    fun provideRetrofit(): WeatherService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(NetworkRequestAdapterFactory.create())
        .build()
        .create(WeatherService::class.java)

    @Named(ZIP_URL)
    @Singleton
    @Provides
    fun provideRetrofit2(): WeatherService = Retrofit.Builder()
        .baseUrl(ZIP_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(NetworkRequestAdapterFactory.create())
        .build()
        .create(WeatherService::class.java)
}