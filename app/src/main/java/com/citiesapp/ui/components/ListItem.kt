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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.citiesapp.ui.theme.CitiesAppTheme
import com.citiesapp.ui.theme.mockCities
import com.domain.model.CityModel

@Composable
fun CityItem(city: CityModel,
             modifier: Modifier = Modifier,
             onFavoriteClick: (Boolean) -> Unit
) {
    var favorite by remember { mutableStateOf(city.isFavorite) }
    Row(
        modifier = modifier
            .shadow(10.dp, shape = RoundedCornerShape(16.dp)) //TODO: Agregar dimen a dimens.xml
            .background(Color.White)
            .fillMaxWidth()
            .padding(16.dp), //TODO: Agregar dimen a dimens.xml
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            RegularText(text = "${city.name}, ${city.country}", fontSize = 18.sp) //TODO: Agregar dimen a dimens.xml y texto a string.xml
            RegularText(text = "${city.coordenates.first}, ${city.coordenates.second}", fontSize = 14.sp, color = Color.Gray) //TODO: Agregar dimen a dimens.xml y texto a string.xml
        }
        IconButton(onClick = {
            favorite = !favorite
            onFavoriteClick(favorite)
        }) {
            Icon(
                imageVector = if (favorite) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = "Favorite", //TODO: Agregar texto a string.xml
                tint = if (favorite) Color.Yellow else Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListItemPreview() {
    CitiesAppTheme {
        Column {
            CityItem(mockCities.first(), modifier = Modifier.padding(15.dp)) {} //TODO: Agregar dimen a dimens.xml
            CityItem(mockCities[2], modifier = Modifier.padding(15.dp)) {} //TODO: Agregar dimen a dimens.xml
        }
    }
}