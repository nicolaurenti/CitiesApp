package com.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.data.database.model.CityDBModel

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(citiesList: List<CityDBModel>)

    @Query("SELECT * FROM cities WHERE id = :cityId")
    suspend fun getCityById(cityId: Int): CityDBModel?

    @Query("SELECT * FROM cities LIMIT 50 OFFSET :offset")
    suspend fun getCities(offset: Int = 0): List<CityDBModel>

    @Query("SELECT * FROM cities WHERE isFavorite = 1")
    suspend fun getFavoriteCities(): List<CityDBModel>

    @Query("UPDATE cities SET isFavorite = CASE WHEN isFavorite = 1 THEN 0 ELSE 1 END WHERE id = :cityId")
    suspend fun updateFavorite(cityId: Int)

    @Query("SELECT * FROM cities WHERE name LIKE :query || '%'")
    fun searchCitiesStartingWith(query: String): List<CityDBModel>

    @Delete
    suspend fun deleteCity(city: CityDBModel)
}
