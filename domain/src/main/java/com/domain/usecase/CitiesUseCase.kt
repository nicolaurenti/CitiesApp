package com.domain.usecase

import com.domain.model.CityModel
import com.domain.repository.CitiesRepository
import com.domain.util.CoroutineResult
import javax.inject.Inject

interface CitiesUseCase {
    suspend fun getCities(): CoroutineResult<List<CityModel>>
    suspend fun getMoreCities(offset: Int): CoroutineResult<List<CityModel>>
    suspend fun getFavoritesCities(): CoroutineResult<List<CityModel>>
    suspend fun updateFavorite(cityId: Int)
    suspend fun searchCities(query: String): CoroutineResult<List<CityModel>>
}

class CitiesUseCaseImpl @Inject constructor(
    private val citiesRepository: CitiesRepository,
) : CitiesUseCase {

    override suspend fun getCities(): CoroutineResult<List<CityModel>> {
        return citiesRepository.getCities()
    }

    override suspend fun getMoreCities(offset: Int): CoroutineResult<List<CityModel>> {
        return citiesRepository.getMoreCities(offset)
    }

    override suspend fun getFavoritesCities(): CoroutineResult<List<CityModel>> {
        return when (val result = citiesRepository.getFavoriteCities()) {
            is CoroutineResult.Success -> {
                CoroutineResult.Success(result.data)
            }

            is CoroutineResult.Failure -> {
                CoroutineResult.Failure(Exception("ERROR"))
            }
        }
    }

    override suspend fun updateFavorite(cityId: Int) {
        citiesRepository.updateFavorite(cityId)
    }

    override suspend fun searchCities(query: String): CoroutineResult<List<CityModel>> {
        return citiesRepository.searchCities(query)
    }
}
