package com.domain.di

import com.domain.repository.CitiesRepository
import com.domain.usecase.CitiesUseCase
import com.domain.usecase.CitiesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideCitiesUseCase(citiesRepository: CitiesRepository): CitiesUseCase =
        CitiesUseCaseImpl(citiesRepository)
}
