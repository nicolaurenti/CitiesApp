package com.citiesapp.viewmodel

import androidx.collection.mutableIntListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.citiesapp.ui.theme.mockCities
import com.domain.model.CityModel
import com.domain.usecase.CitiesUseCase
import com.domain.util.CoroutineResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class CitiesViewModel @Inject constructor(private val citiesUseCase: CitiesUseCase) : ViewModel() {

    private var favoritesCities: List<CityModel> = listOf()
    private var citiesList: List<CityModel> = listOf()
    private val _errorStatus = MutableStateFlow(CitiesErrors.NO_ERROR)
    private val _cities = MutableStateFlow<List<CityModel>>(emptyList())
    val cities: StateFlow<List<CityModel>> = _cities

    fun getCities() = viewModelScope.launch {
        withContext(Dispatchers.IO) { citiesUseCase.getCities() }.let { it ->
            when (it) {
                is CoroutineResult.Failure -> _errorStatus.value = CitiesErrors.SERVER
                is CoroutineResult.Success -> {
                    val citiesFromRepository = it.data.sortedBy { it.name+it.country }
                    _cities.value = citiesFromRepository
                    citiesList = citiesFromRepository
                    favoritesCities = citiesList.filter { city -> city.isFavorite }
                }
            }
        }
    }

    fun onShowingFavorites(showFavorites: Boolean) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            if (showFavorites) {
                _cities.value = favoritesCities
            } else {
                _cities.value = citiesList
            }
        }
    }

    fun getSearchedCities(query: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            _cities.value.filter { it.name.lowercase().contains(query.lowercase()) }
        }
    }

    fun onFavoriteClick(id: Int) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            citiesUseCase.updateFavorite(id)
        }
    }

    enum class CitiesErrors {
        NO_ERROR,
        SERVER
    }
}
