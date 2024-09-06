package com.mccarty.fivedayweather.module

import com.mccarty.fivedayweather.domain.FetchWeather
import com.mccarty.fivedayweather.domain.FetchWeatherRepository
import com.mccarty.fivedayweather.domain.FetchWeatherUseCase
import com.mccarty.fivedayweather.domain.InsertWeather
import com.mccarty.fivedayweather.domain.InsertWeatherUseCas
import com.mccarty.fivedayweather.domain.Repository
import com.mccarty.fivedayweather.repository.GetLocalRepository
import com.mccarty.fivedayweather.repository.InsertLocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class InterfaceModule {
    @Binds
    abstract fun provideWeatherRepository(fetchWeatherRepository: FetchWeatherRepository): Repository

   // @Binds
    //abstract fun provideFetchWeatherRepository(fetchWeatherRepository: FetchWeatherRepository): GetLocalRepository

    @Binds
    abstract fun provideFetchWeatherRepository(fetchWeatherRepository: FetchWeatherRepository): InsertLocalRepository

    @Binds
    abstract fun provideFetchWeatherUseCase(fetchWeatherUseCase: FetchWeatherUseCase): FetchWeather

    @Binds
    abstract fun provideInsertWeatherUseCas(fetchInsertWeatherUseCas: InsertWeatherUseCas): InsertWeather
}