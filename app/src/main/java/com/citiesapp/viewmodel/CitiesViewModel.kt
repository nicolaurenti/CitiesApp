package com.citiesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.model.CityModel
import com.domain.usecase.CitiesUseCase
import com.domain.util.CoroutineResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class CitiesViewModel @Inject constructor(private val citiesUseCase: CitiesUseCase) : ViewModel() {

    private var currentPage = 0
    private var cities = mutableListOf<CityModel>()
    private var showingFavorites = false
    private val _filteredCities = MutableStateFlow<List<CityModel>>(emptyList())
    val filteredCities: StateFlow<List<CityModel>> = _filteredCities
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    private val _errorStatus = MutableStateFlow(CitiesErrors.NO_ERROR)
    val errorStatus: StateFlow<CitiesErrors> = _errorStatus.asStateFlow()

    fun getCities() = viewModelScope.launch {
        withContext(Dispatchers.IO) { citiesUseCase.getCities() }.let { it ->
            cities = mutableListOf()
            currentPage = 0
            when (it) {
                is CoroutineResult.Failure -> _errorStatus.value = CitiesErrors.SERVER
                is CoroutineResult.Success -> {
                    val citiesFromRepository = it.data
                    cities.addAll(citiesFromRepository)
                    _filteredCities.value = cities
                }
            }
        }
    }

    fun onShowingFavorites(showFavorites: Boolean) = viewModelScope.launch {
        this@CitiesViewModel.showingFavorites = showFavorites
        if (showFavorites) {
            withContext(Dispatchers.IO) { citiesUseCase.getFavoritesCities() }.let {
                when (it) {
                    is CoroutineResult.Failure -> {
                        _errorStatus.value = CitiesErrors.INTERNAL_ERROR
                        _filteredCities.value = emptyList()

                    }

                    is CoroutineResult.Success -> {
                        _filteredCities.value = it.data
                    }
                }
            }
        } else {
            _filteredCities.value = cities
        }
    }

    fun loadMoreCities() = viewModelScope.launch {
        _isLoading.value = true
        currentPage += 50
        withContext(Dispatchers.IO) {
            citiesUseCase.getMoreCities(currentPage)
        }.let { result ->
            when (result) {
                is CoroutineResult.Failure -> _errorStatus.value = CitiesErrors.INTERNAL_ERROR
                is CoroutineResult.Success -> {
                    cities.addAll(result.data)
                    _filteredCities.value = cities
                    _isLoading.value = false
                }
            }
        }
    }

    fun getSearchedCities(query: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            if (query.isNotEmpty()) {
                citiesUseCase.searchCities(query).let {
                    when (it) {
                        is CoroutineResult.Failure -> {
                            _errorStatus.value = CitiesErrors.CITIES_SEARCH_FAILED
                            _filteredCities.value = mutableListOf()
                        }

                        is CoroutineResult.Success -> {
                            cities =
                                if (showingFavorites)
                                    it.data.filter { city -> city.isFavorite == showingFavorites }.toMutableList()
                                else
                                    it.data.toMutableList()

                            _filteredCities.value = cities
                        }
                    }
                }
            } else {
                getCities()
            }
        }
    }

    fun onFavoriteClick(id: Int) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            cities.find { it.id == id }?.let {
                it.isFavorite = !it.isFavorite
            }
            citiesUseCase.updateFavorite(id)
        }
    }

    enum class CitiesErrors {
        NO_ERROR,
        INTERNAL_ERROR,
        SERVER,
        CITIES_SEARCH_FAILED
    }
}
