package com.data.repository

import com.data.database.dao.CityDao
import com.data.database.model.toDBModel
import com.data.database.model.toModel
import com.data.service.CitiesService
import com.domain.model.CityModel
import com.domain.repository.CitiesRepository
import com.domain.util.CoroutineResult
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val citiesDao: CityDao,
    private val service: CitiesService
) : CitiesRepository {

    override suspend fun getCities(): CoroutineResult<List<CityModel>> {
        val localCities = citiesDao.getAllCities()
        return if (localCities.isNotEmpty()) {
            CoroutineResult.Success(localCities.map { it.toModel() })
        } else {
            when (val remoteResult = service.getCitiesList()) {
                is CoroutineResult.Success -> {
                    citiesDao.insertCities(remoteResult.data.map { it.toDBModel() })
                    remoteResult
                }

                is CoroutineResult.Failure -> CoroutineResult.Failure(Exception("ERROR"))
            }
        }
    }

    override suspend fun getFavoriteCities(): CoroutineResult<List<CityModel>> {
        val localCities = citiesDao.getFavoriteCities()
        return if (localCities.isNotEmpty()) {
            CoroutineResult.Success(localCities.map { it.toModel() })
        } else {
            when (val remoteResult = service.getCitiesList()) {
                is CoroutineResult.Success -> {
                    citiesDao.insertCities(remoteResult.data.map { it.toDBModel() })
                    remoteResult
                }

                is CoroutineResult.Failure -> CoroutineResult.Failure(Exception("ERROR"))
            }
        }
    }

    override suspend fun updateFavorite(cityId: Int) {
        citiesDao.updateFavorite(cityId)
    }
}