package com.domain.usecase

import com.domain.model.CityModel
import com.domain.repository.CitiesRepository
import com.domain.util.CoroutineResult
import com.domain.util.mockCities
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.core.ValueClassSupport.boxedValue
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CitiesUseCaseTest {

    private val testDispatcher = StandardTestDispatcher(TestCoroutineScheduler())
    private lateinit var useCase: CitiesUseCase
    private val citiesRepository: CitiesRepository = mockk()

    @Before
    fun setup() {
        useCase = CitiesUseCaseImpl(citiesRepository)
    }

    @Test
    fun `getCities success`() = runTest(testDispatcher) {
        coEvery { citiesRepository.getCities() } returns CoroutineResult.Success(mockCities)

        val result = useCase.getCities()
        assertEquals(mockCities, (result as CoroutineResult.Success).data)
    }

    @Test
    fun `getCities failure`() = runTest(testDispatcher) {
        coEvery { citiesRepository.getCities() } returns CoroutineResult.Failure(Exception("Error"))

        val result = useCase.getCities()
        assert(result is CoroutineResult.Failure)
    }

    @Test
    fun `updateFavorite calls repository`() = runTest(testDispatcher) {
        coEvery { citiesRepository.updateFavorite(1) } returns Unit

        useCase.updateFavorite(1)

        coVerify { citiesRepository.updateFavorite(1) }
    }
}