package com.citiesapp.ui.screens

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.citiesapp.ui.theme.CitiesAppTheme
import com.domain.model.CityModel
import org.junit.Rule
import org.junit.Test

class CitiesListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockCities = listOf(
        CityModel(id = 1, name = "City 1", country = "AR", coordenates = Pair(1.0, 2.0), isFavorite = false),
        CityModel(id = 2, name = "City 2", country = "AR", coordenates = Pair(3.0, 4.0), isFavorite = false),
        CityModel(id = 3, name = "City 3", country = "AR", coordenates = Pair(5.0, 6.0), isFavorite = false)
    )

    @Test
    fun testCitiesListScreen_displayCities() {
        composeTestRule.setContent {
            CitiesAppTheme {
                CitiesListScreen(
                    cities = mockCities,
                    onFavoriteClick = {},
                    onCityClick = {},
                    onShowFavoritesClicked = {},
                    searchValue = {},
                    loadMoreCities = {},
                    isLoading = false
                )
            }
        }

        mockCities.forEach { city ->
            composeTestRule.onNodeWithText("${city.name}, ${city.country}").assertIsDisplayed()
        }
    }

    @Test
    fun testSearchBarFunctionality() {
        composeTestRule.setContent {
            CitiesAppTheme {
                CitiesListScreen(
                    cities = mockCities,
                    onFavoriteClick = {},
                    onCityClick = {},
                    onShowFavoritesClicked = {},
                    searchValue = { searchQuery ->
                        println("Search query: $searchQuery")
                    },
                    loadMoreCities = {},
                    isLoading = false
                )
            }
        }

        composeTestRule.onNodeWithTag("searchBarCustom").performTextInput("City 1")
        composeTestRule.onNodeWithTag("searchBarCustom").assertTextEquals("City 1")
    }

    @Test
    fun testFavoriteClick() {
        composeTestRule.setContent {
            CitiesAppTheme {
                CitiesListScreen(
                    cities = mockCities,
                    onFavoriteClick = { cityId ->
                        assert(cityId == 1)
                    },
                    onCityClick = {},
                    onShowFavoritesClicked = {},
                    searchValue = {},
                    loadMoreCities = {},
                    isLoading = false
                )
            }
        }

        composeTestRule.onNodeWithTag("favoriteButton_1").performClick()
    }
}
