package com.data.di

import android.content.Context
import androidx.room.Room
import com.data.database.AppDatabase
import com.data.database.dao.CityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DB = "CitiesDatabase"

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, DB)
            .build()
    }

    @Provides
    @Singleton
    fun provideCitiesDao(citiesDatabase: AppDatabase): CityDao = citiesDatabase.cityDao()
}
