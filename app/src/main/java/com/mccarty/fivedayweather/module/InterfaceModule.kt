package com.mccarty.fivedayweather.module

import com.mccarty.fivedayweather.domain.FetchWeatherRepository
import com.mccarty.fivedayweather.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class InterfaceModule {
    @Binds
    abstract fun provideWeatherRepository(fetchWeatherRepository: FetchWeatherRepository): Repository
}