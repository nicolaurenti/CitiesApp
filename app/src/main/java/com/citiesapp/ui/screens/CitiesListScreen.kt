package com.citiesapp.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.citiesapp.R
import com.citiesapp.ui.components.PaginatedCityList
import com.citiesapp.ui.components.SearchBarCustom
import com.citiesapp.ui.components.TopBar
import com.citiesapp.ui.theme.CitiesAppTheme
import com.citiesapp.ui.theme.mockCities
import com.domain.model.CityModel

@Composable
fun CitiesListScreen(
    cities: List<CityModel>,
    onFavoriteClick: (Int) -> Unit,
    onCityClick: (CityModel) -> Unit,
    onShowFavoritesClicked: (Boolean) -> Unit,
    searchValue: (String) -> Unit,
    loadMoreCities: () -> Unit = {},
    isLoading: Boolean = false
) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            CitiesListLandscapeView(
                cities = cities,
                onFavoriteClick = onFavoriteClick,
                onCityClick = onCityClick,
                onShowFavoritesClicked = onShowFavoritesClicked,
                searchValue = searchValue,
                loadMoreCities = loadMoreCities,
                isLoading = isLoading
            )
        }

        Configuration.ORIENTATION_PORTRAIT -> {
            CitiesListPortraitView(
                cities = cities,
                onFavoriteClick = onFavoriteClick,
                onCityClick = onCityClick,
                onShowFavoritesClicked = onShowFavoritesClicked,
                searchValue = searchValue,
                loadMoreCities = loadMoreCities,
                isLoading = isLoading
            )
        }

        else -> {
            CitiesListPortraitView(
                cities = cities,
                onFavoriteClick = onFavoriteClick,
                onCityClick = onCityClick,
                onShowFavoritesClicked = onShowFavoritesClicked,
                searchValue = searchValue,
                loadMoreCities = loadMoreCities,
                isLoading = isLoading
            )
        }
    }
}


@Composable
private fun CitiesListPortraitView(
    cities: List<CityModel>,
    onFavoriteClick: (Int) -> Unit,
    onCityClick: (CityModel) -> Unit,
    loadMoreCities: () -> Unit,
    isLoading: Boolean,
    onShowFavoritesClicked: (Boolean) -> Unit,
    searchValue: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(
            title = stringResource(id = R.string.cities_title),
            backEnabled = false,
            onShowFavoritesClicked = onShowFavoritesClicked
        )
        SearchBarCustom(searchValue)
        PaginatedCityList(
            cities = cities,
            loadMoreCities = loadMoreCities,
            isLoading = isLoading,
            onCityClick = onCityClick,
            onFavoriteClick = onFavoriteClick
        )
    }
}

@Composable
fun CitiesListLandscapeView(
    cities: List<CityModel>,
    onFavoriteClick: (Int) -> Unit,
    onCityClick: (CityModel) -> Unit,
    loadMoreCities: () -> Unit,
    isLoading: Boolean,
    onShowFavoritesClicked: (Boolean) -> Unit,
    searchValue: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.White)
    ) {
        TopBar(
            title = stringResource(id = R.string.cities_title),
            backEnabled = false,
            onShowFavoritesClicked = onShowFavoritesClicked
        )
        SearchBarCustom(searchValue)
        PaginatedCityList(
            cities = cities,
            loadMoreCities = loadMoreCities,
            isLoading = isLoading,
            onCityClick = onCityClick,
            onFavoriteClick = onFavoriteClick
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun CitiesListScreenPreview() {
    CitiesAppTheme {
        Column(Modifier.fillMaxSize()) {
            CitiesListScreen(mockCities, { _ -> }, { _ -> }, { _ -> }, { _ -> }, {}, false)
        }
    }
}