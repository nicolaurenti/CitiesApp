package com.citiesapp.di

import com.data.di.DataModule
import com.domain.di.DomainModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [DataModule::class, DomainModule::class])
@InstallIn(SingletonComponent::class)
class AppModule