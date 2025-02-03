package com.data.response

import com.domain.model.CityModel
import com.google.gson.annotations.SerializedName

data class CityResponse(
    val country: String,
    val name: String,
    @SerializedName("_id") val id: Int,
    val coord: Coord
)

data class Coord(
    val lon: Double,
    val lat: Double
)

fun CityResponse.toModel() = CityModel(
    country = country,
    name = name,
    id = id,
    isFavorite = false,
    coordenates = Pair(coord.lat, coord.lon)
)