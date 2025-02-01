package com.citiesapp.ui.screens.citiesListScreen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.citiesapp.R
import com.citiesapp.ui.components.CityItem
import com.citiesapp.ui.components.SearchBarCustom
import com.citiesapp.ui.components.TopBar
import com.citiesapp.ui.theme.CitiesAppTheme
import com.citiesapp.ui.theme.Dimen15dp
import com.citiesapp.ui.theme.Dimen2dp
import com.citiesapp.ui.theme.Dimen5dp
import com.citiesapp.ui.theme.Dimen10dp
import com.citiesapp.ui.theme.mockCities
import com.domain.model.CityModel

@Composable
fun CitiesListScreen(cities: List<CityModel>, onFavoriteClick: (Boolean, Int) -> Unit, onCityClick: (CityModel) -> Unit) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            CitiesListLandscapeView(cities, onFavoriteClick, onCityClick)
        }

        Configuration.ORIENTATION_PORTRAIT -> {
            CitiesListPortraitView(cities, onFavoriteClick, onCityClick)
        }
        else -> {
            CitiesListPortraitView(cities, onFavoriteClick, onCityClick)
        }
    }
}


@Composable
private fun CitiesListPortraitView(cities: List<CityModel>, onFavoriteClick: (Boolean, Int) -> Unit, onCityClick: (CityModel) -> Unit) {
    Column(modifier = Modifier.wrapContentSize()) {
        TopBar(title = stringResource(id = R.string.cities_title))
        SearchBarCustom({})
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentPadding = PaddingValues(Dimen15dp)
        ) {
            itemsIndexed(cities) { _, city ->
                CityItem(
                    city = city,
                    modifier = Modifier
                        .clickable { onCityClick(city) }
                        .padding(bottom = Dimen10dp),
                    onFavoriteClick = { favorite ->
                        onFavoriteClick(favorite, city.id)
                    }
                )
            }
        }
    }
}

@Composable
fun CitiesListLandscapeView(cities: List<CityModel>, onFavoriteClick: (Boolean, Int) -> Unit, onCityClick: (CityModel) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        TopBar(title = stringResource(id = R.string.cities_title), backEnabled = false)
        SearchBarCustom({})
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = Dimen5dp)
        ) {
            itemsIndexed(cities) { _, city ->
                CityItem(
                    city = city,
                    modifier = Modifier
                        .padding(bottom = Dimen2dp)
                        .clickable { onCityClick(city) },
                    onFavoriteClick = { favorite ->
                        onFavoriteClick(favorite, city.id)
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CitiesListScreenPreview() {
    CitiesAppTheme {
        Column(Modifier.fillMaxSize()) {
            CitiesListScreen(mockCities, { _, _ -> }) { _ -> }
        }
    }
}