package com.citiesapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.citiesapp.ui.screens.CityDetailScreen
import com.citiesapp.ui.theme.CitiesAppTheme
import com.domain.util.mockCities
import org.junit.Rule
import org.junit.Test

class CityDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val city = mockCities.first()

    @Test
    fun testCityDetailScreenPortrait() {
        composeTestRule.setContent {
            CitiesAppTheme {
                CityDetailScreen(city = city, onBackPressed = {}, onFavoriteClick = {})
            }
        }

        composeTestRule.onNodeWithText("City Detail").assertIsDisplayed()
        composeTestRule.onNodeWithText(city.name).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Back").performClick()
    }

    @Test
    fun testFavoriteButtonClick() {
        var favoriteClicked = false
        composeTestRule.setContent {
            CitiesAppTheme {
                CityDetailScreen(
                    city = city,
                    onBackPressed = {},
                    onFavoriteClick = { favoriteClicked = true }
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("Favorite").performClick()
        composeTestRule.onNodeWithText("Tandil, AR").assertIsDisplayed()
        assert(favoriteClicked)
    }

    @Test
    fun testGoogleMapMarker() {
        composeTestRule.setContent {
            CitiesAppTheme {
                CityDetailScreen(city = city, onBackPressed = {}, onFavoriteClick = {})
            }
        }

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("City Detail").assertIsDisplayed()
        composeTestRule.onNodeWithText("Tandil, AR").assertIsDisplayed()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("google_maps").assertIsDisplayed()
    }
}
