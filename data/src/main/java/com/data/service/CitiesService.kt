package com.data.service

import com.data.api.CitiesApi
import com.data.response.CityResponse
import com.data.response.toModel
import com.domain.model.CityModel
import com.domain.util.CoroutineResult
import javax.inject.Inject

interface CitiesService {
    fun getCitiesList(): CoroutineResult<List<CityModel>>
}

class CitiesServiceImpl @Inject constructor(private val citiesApi: CitiesApi) : CitiesService {

        override fun getCitiesList(): CoroutineResult<List<CityModel>> {
            return try {
                val callResponse = citiesApi.getCitiesList()
                val response = callResponse.execute()
                var citiesList = emptyList<CityResponse>()
                if (response.isSuccessful) {
                    response.body()?.let {
                        citiesList = it
                    }
                }
                CoroutineResult.Success(citiesList.map { it.toModel() })
            } catch (e: Exception) {
                CoroutineResult.Failure(java.lang.Exception())
            }
        }
}