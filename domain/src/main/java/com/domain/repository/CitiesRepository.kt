package com.domain.repository

import com.domain.model.CityModel
import com.domain.util.CoroutineResult

interface CitiesRepository {
    suspend fun getCities(): CoroutineResult<List<CityModel>>
    suspend fun getFavoriteCities(): CoroutineResult<List<CityModel>>
    suspend fun updateFavorite(cityId: Int)
}