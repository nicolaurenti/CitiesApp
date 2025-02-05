package com.citiesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.citiesapp.R
import com.citiesapp.ui.theme.CitiesAppTheme
import com.citiesapp.ui.theme.CustomRed
import com.citiesapp.ui.theme.Dimen10dp
import com.citiesapp.ui.theme.Dimen15dp
import com.citiesapp.ui.theme.TextSizeDimen14dp
import com.citiesapp.ui.theme.TextSizeDimen20dp
import com.domain.model.CityModel
import com.domain.util.mockCities

@Composable
fun CityItem(city: CityModel,
             modifier: Modifier = Modifier,
             onFavoriteClick: (Int) -> Unit
) {
    var favorite by remember { mutableStateOf(false) }
    favorite = city.isFavorite
    Row(
        modifier = modifier
            .shadow(Dimen10dp, shape = RoundedCornerShape(Dimen15dp))
            .background(Color.White)
            .fillMaxWidth()
            .padding(Dimen15dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            RegularText(text = stringResource(id = R.string.formated_city_data, city.name, city.country), fontSize = TextSizeDimen20dp, modifier = Modifier.testTag("cityText_${city.id}"))
            RegularText(text = stringResource(id = R.string.formated_city_data, city.coordenates.first, city.coordenates.second), fontSize = TextSizeDimen14dp, color = Color.Gray)
        }
        IconButton(onClick = {
            favorite = !favorite
            onFavoriteClick(city.id)
        }, Modifier.testTag("favoriteButton_${city.id}")) {
            Icon(
                imageVector = if (favorite) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = stringResource(id = R.string.favorites_content_description),
                tint = if (favorite) CustomRed else Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListItemPreview() {
    CitiesAppTheme {
        Column {
            CityItem(mockCities.first(), modifier = Modifier.padding(Dimen15dp)) {}
            CityItem(mockCities[2], modifier = Modifier.padding(Dimen15dp)) {}
        }
    }
}