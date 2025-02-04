package com.citiesapp.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.citiesapp.ui.screens.citiesListScreen.CitiesListScreen
import com.citiesapp.ui.screens.cityDetail.CityDetailScreen
import com.domain.model.CityModel
import com.google.gson.Gson

@Composable
fun AppNavigation(
    cities: List<CityModel>,
    onFavoriteClick: (Int) -> Unit,
    onShowFavoritesClicked: (Boolean) -> Unit,
    searchValue: (String) -> Unit,
    loadMoreCities: () -> Unit,
    isLoading: Boolean
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.CitiesListScreen.route) {
        composable(route = AppScreen.CitiesListScreen.route) {
            CitiesListScreen(
                cities = cities,
                onFavoriteClick = onFavoriteClick,
                onCityClick = { city ->
                    val cityJson = Uri.encode(Gson().toJson(city))
                    navController.navigate("${AppScreen.CityDetailScreen.route}/$cityJson")
                },
                onShowFavoritesClicked = onShowFavoritesClicked,
                searchValue = searchValue,
                loadMoreCities = loadMoreCities,
                isLoading = isLoading
            )
        }

        composable(
            route = "${AppScreen.CityDetailScreen.route}/{city}",
            arguments = listOf(navArgument("city") { type = NavType.StringType })
        ) { backStackEntry ->
            val cityJson = backStackEntry.arguments?.getString("city")
            val city = cityJson?.let { Gson().fromJson(it, CityModel::class.java) }

            city?.let {
                CityDetailScreen(
                    city = it,
                    onBackPressed = { navController.popBackStack() },
                    onFavoriteClick = { id -> onFavoriteClick(id) }
                )
            }
        }
    }
}
