package com.domain.model

data class CityModel(
    var country: String,
    var name: String,
    var id: Int,
    var coordenates: Pair<Double, Double>,
    var isFavorite: Boolean
)
