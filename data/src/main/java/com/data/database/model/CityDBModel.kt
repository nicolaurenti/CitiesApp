package com.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.domain.model.CityModel

@Entity(tableName = "cities")
class CityDBModel(
    @ColumnInfo(name = "country") val country: String,
    @ColumnInfo(name = "name") val name: String,
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "lat") val lat: Double,
    @ColumnInfo(name = "lon") val lon: Double,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean
)

fun CityDBModel.toModel() = CityModel(
    country = country,
    name = name,
    id = id,
    isFavorite = isFavorite,
    coordenates = Pair(lat, lon)
)


fun CityModel.toDBModel() = CityDBModel(
    country = country,
    name = name,
    id = id,
    isFavorite = isFavorite,
    lat = coordenates.first,
    lon = coordenates.second
)