package com.data.di

import com.domain.di.DomainModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [ApiModule::class, ServiceModule::class, RepositoryModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
object DataModule