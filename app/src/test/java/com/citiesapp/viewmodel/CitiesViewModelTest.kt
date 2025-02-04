package com.citiesapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.citiesapp.viewmodel.CitiesViewModel.CitiesErrors
import com.citiesapp.viewmodel.CitiesViewModel.CitiesErrors.NO_ERROR
import com.citiesapp.viewmodel.CitiesViewModel.CitiesErrors.SERVER
import com.domain.model.CityModel
import com.domain.usecase.CitiesUseCase
import com.domain.util.CoroutineResult
import com.domain.util.mockCities
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
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
            val listOfEmittedResult = mutableListOf<CitiesErrors>()
            val job = viewModel.errorStatus.onEach { result ->
                listOfEmittedResult.add(result)
            }.launchIn(CoroutineScope(UnconfinedTestDispatcher(testScheduler)))

            coEvery { citiesUseCase.getCities() } returns CoroutineResult.Failure(Exception("ERROR"))

            viewModel.getCities().join()

            assert(listOfEmittedResult[0] == NO_ERROR)
            assert(listOfEmittedResult[1] == SERVER)

            job.cancel()
        }
    }

    @Test
    fun onFavoriteClickCallsUseCase() {
        runTest(UnconfinedTestDispatcher()) {
            val cityId = 1
            coEvery { citiesUseCase.updateFavorite(cityId) } returns Unit
            viewModel.onFavoriteClick(cityId)
            coVerify { citiesUseCase.updateFavorite(cityId) }
        }
    }
}
