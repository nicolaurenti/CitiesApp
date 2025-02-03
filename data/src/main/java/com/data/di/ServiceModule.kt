package com.data.di

import com.data.api.CitiesApi
import com.data.service.CitiesService
import com.data.service.CitiesServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    fun provideCitiesService(citiesApi: CitiesApi): CitiesService = CitiesServiceImpl(citiesApi)
}