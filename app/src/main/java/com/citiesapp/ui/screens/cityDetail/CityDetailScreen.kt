package com.citiesapp.ui.screens.cityDetail

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.citiesapp.R
import com.citiesapp.ui.components.CityCard
import com.citiesapp.ui.components.TopBar
import com.citiesapp.ui.theme.CitiesAppTheme
import com.citiesapp.ui.theme.Dimen50dp
import com.citiesapp.ui.theme.mockCities
import com.domain.model.CityModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun CityDetailScreen(city: CityModel, onBackPressed: () -> Unit, onFavoriteClick: (Int) -> Unit) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            CityDetailLandscapeView(city, onBackPressed, onFavoriteClick)
        }

        Configuration.ORIENTATION_PORTRAIT -> {
            CityDetailPortraitView(city, onBackPressed, onFavoriteClick)
        }
        else -> {
            CityDetailPortraitView(city, onBackPressed, onFavoriteClick)
        }
    }


}

@Composable
private fun CityDetailPortraitView(city: CityModel, onBackPressed: () -> Unit, onFavoriteClick: (Int) -> Unit) {
    val recipeLocation = LatLng(city.coordenates.first, city.coordenates.second)
    Log.i("CityDetailPortraitView", "CityDetailPortraitView: $recipeLocation")
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(recipeLocation, 10f)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(title = stringResource(id = R.string.city_detail_title), onBackPressed = { onBackPressed() })
        CityCard(
            city = city,
            onFavoriteClick = onFavoriteClick
        )
        GoogleMap(
            modifier = Modifier.fillMaxWidth().weight(1f),
            cameraPositionState = cameraPositionState,
        ) {
            Marker(
                state = MarkerState(position = recipeLocation),
                title = "Singapore",
                snippet = "Marker in Singapore",
            )
        }
    }
}

@Composable
private fun CityDetailLandscapeView(city: CityModel, onBackPressed: () -> Unit, onFavoriteClick: (Int) -> Unit) {
    val recipeLocation = LatLng(city.coordenates.first, city.coordenates.second)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(recipeLocation, 10f)
    }
    Row(modifier = Modifier.fillMaxSize()) {
        TopBar(title = stringResource(id = R.string.city_detail_title), onBackPressed = { onBackPressed() })
        CityCard(
            city = city,
            modifier = Modifier.weight(0.5f),
            onFavoriteClick = { id: Int -> onFavoriteClick(id) }
        )
        GoogleMap(
            modifier = Modifier.size(Dimen50dp),
            cameraPositionState = cameraPositionState,
        ) {
            Marker(
                state = MarkerState(position = recipeLocation),
                title = "Singapore",
                snippet = "Marker in Singapore",
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CityDetailScreenPreview() {
    CitiesAppTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            CityDetailScreen(mockCities.first(), {}) {_ ->}
        }
    }
}