package com.citiesapp.ui.screens.cityDetail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.citiesapp.R
import com.citiesapp.ui.components.CityCard
import com.citiesapp.ui.components.TopBar
import com.citiesapp.ui.theme.CitiesAppTheme
import com.citiesapp.ui.theme.SpacerWidth
import com.citiesapp.ui.theme.mockCities
import com.domain.model.CityModel

@Composable
fun CityDetailScreen(city: CityModel, onBackPressed: () -> Unit, onFavoriteClick: (Boolean, Int) -> Unit) {
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
private fun CityDetailPortraitView(city: CityModel, onBackPressed: () -> Unit, onFavoriteClick: (Boolean, Int) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(title = stringResource(id = R.string.city_detail_title), onBackPressed = { onBackPressed() })
        CityCard(
            city = city,
            onFavoriteClick = onFavoriteClick
        )
        //TODO(Agregar el mapa aca)
    }
}

@Composable
private fun CityDetailLandscapeView(city: CityModel, onBackPressed: () -> Unit, onFavoriteClick: (Boolean, Int) -> Unit) {
    Row(modifier = Modifier.fillMaxSize()) {
        TopBar(title = stringResource(id = R.string.city_detail_title), onBackPressed = { onBackPressed() })
        CityCard(
            city = city,
            modifier = Modifier.weight(0.5f),
            onFavoriteClick = { isFavourite: Boolean, id: Int -> onFavoriteClick(isFavourite, id) }
        )
        Spacer(modifier = Modifier
            .width(SpacerWidth)
            .weight(0.5f)) //TODO(Agregar el mapa aca)
    }
}


@Preview(showBackground = true)
@Composable
private fun CityDetailScreenPreview() {
    CitiesAppTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            CityDetailScreen(mockCities.first(), {}) { _, _ ->}
        }
    }
}