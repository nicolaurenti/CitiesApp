package com.citiesapp.viewmodel

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.citiesapp.ui.theme.mockCities
import com.domain.model.CityModel
import com.domain.usecase.CitiesUseCase
import com.domain.util.CoroutineResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CitiesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    val sortedList = mockCities.sortedBy { it.name + it.country }.subList(0, 50)

    private lateinit var viewModel: CitiesViewModel

    @MockK
    private lateinit var citiesUseCase: CitiesUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = CitiesViewModel(citiesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getCitiesSuccess() {
        runTest(UnconfinedTestDispatcher()) {
            val listOfEmittedResult = mutableListOf<List<CityModel>>()
            val job = viewModel.filteredCities.onEach(listOfEmittedResult::add).launchIn(
                CoroutineScope(UnconfinedTestDispatcher(testScheduler))
            )
            coEvery { citiesUseCase.getCities() } returns CoroutineResult.Success(
                sortedList
            )
            viewModel.getCities().join()
            assert(listOfEmittedResult[1].size == mockCities.subList(0, 50).size)
            assert(listOfEmittedResult[0] == mutableListOf<CityModel>())
            assert(listOfEmittedResult[1] == sortedList)
            job.cancel()
        }
    }

    @Test
    fun getCitiesFailure() {
        runTest(UnconfinedTestDispatcher()) {
            val listOfEmittedResult = mutableListOf<List<CityModel>>()
            val job = viewModel.filteredCities.onEach(listOfEmittedResult::add).launchIn(
                CoroutineScope(UnconfinedTestDispatcher(testScheduler))
            )
            coEvery { citiesUseCase.getCities() } returns CoroutineResult.Failure(CitiesViewModel.CitiesErrors.SERVER)
            viewModel.getCities().join()
            assert(listOfEmittedResult[1].size == mockCities.subList(0, 50).size)
            assert(listOfEmittedResult[0] == mutableListOf<CityModel>())
            assert(listOfEmittedResult[1] == sortedList)
            job.cancel()
        }
    }

    /*@Test
    fun `onFavoriteClick calls use case`() = runTest(testDispatcher) {
        // Arrange
        val cityId = 1
        coEvery { citiesUseCase.updateFavorite(cityId) } returns Unit

        // Act
        viewModel.onFavoriteClick(cityId)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        coVerify { citiesUseCase.updateFavorite(cityId) }
    }*/
}
