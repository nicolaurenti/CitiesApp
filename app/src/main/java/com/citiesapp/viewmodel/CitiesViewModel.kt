package com.citiesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.citiesapp.ui.theme.mockCities
import com.domain.model.CityModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CitiesViewModel @Inject constructor() : ViewModel() {

    private val _cities = MutableStateFlow<List<CityModel>>(emptyList())
    val cities: StateFlow<List<CityModel>> = _cities

    fun getCities() = viewModelScope.launch {
        _cities.value = mockCities // TODO: Implementar lógica para obtener las ciudades del repositorio
    }

    fun  onFavoriteClick(isFavorite: Boolean, id: Int) = viewModelScope.launch {
        // TODO: Implementar lógica para actualizar el estado favorito de la ciudad
    }
}
