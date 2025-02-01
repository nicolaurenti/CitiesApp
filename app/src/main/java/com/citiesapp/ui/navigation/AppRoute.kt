package com.citiesapp.ui.navigation

import com.citiesapp.ui.navigation.AppDestination.CITIES_LIST_SCREEN_ROUTE
import com.citiesapp.ui.navigation.AppDestination.CITY_DETAIL_SCREEN_ROUTE

object AppDestination {
    const val CITIES_LIST_SCREEN_ROUTE = "CITIES_LIST_SCREEN_ROUTE"
    const val CITY_DETAIL_SCREEN_ROUTE = "CITY_DETAIL_SCREEN_ROUTE"
}

sealed class AppScreen(val route: String) {
    data object CitiesListScreen : AppScreen(CITIES_LIST_SCREEN_ROUTE)

    data object CityDetailScreen : AppScreen(CITY_DETAIL_SCREEN_ROUTE)
}