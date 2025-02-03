package com.data.di

import com.data.database.dao.CityDao
import com.data.repository.CitiesRepositoryImpl
import com.data.service.CitiesService
import com.domain.repository.CitiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideCitiesRepository(citiesDao: CityDao, citiesService: CitiesService): CitiesRepository =
        CitiesRepositoryImpl(citiesDao, citiesService)
}
