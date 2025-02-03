package com.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.data.database.dao.CityDao
import com.data.database.model.CityDBModel

@Database(entities = [CityDBModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}