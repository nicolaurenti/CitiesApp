package com.citiesapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.citiesapp.R
import com.citiesapp.ui.theme.CitiesAppTheme
import com.citiesapp.ui.theme.mockCities
import com.domain.model.CityModel

@Composable
fun CityCard(
    city: CityModel,
    modifier: Modifier = Modifier,
    onFavoriteClick: (Boolean, Int) -> Unit
) {
    Card(
        modifier = modifier.padding(16.dp), //TODO: Agregar dimen a dimens.xml
        elevation = CardDefaults.cardElevation(10.dp), //TODO: Agregar dimen a dimens.xml
        shape = RoundedCornerShape(16.dp), //TODO: Agregar dimen a dimens.xml
    ) {
        val favorite by remember { mutableStateOf(city.isFavorite) }
            Column(
                modifier = Modifier.padding(16.dp) //TODO: Agregar dimen a dimens.xml
            ) {
                Row {
                    TitleText(
                        text = "${city.name}, ${city.country}",
                        fontSize = 25.sp, //TODO: Agregar dimen a dimens.xml
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = {
                        onFavoriteClick(favorite, city.id)
                    }) {
                        Icon(
                            imageVector = if (favorite) Icons.Filled.Star else Icons.Outlined.Star,
                            contentDescription = "Favorite",
                            tint = if (favorite) Color.Yellow else Color.Gray,
                            modifier = Modifier.size(50.dp) //TODO: Agregar dimen a dimens.xml
                        )
                    }
                }
                DescriptionText(
                    text = "Latitud: ${city.coordenates.first}",
                    fontSize = 21.sp, //TODO: Agregar dimen a dimens.xml
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 10.dp) //TODO: Agregar dimen a dimens.xml
                )
                DescriptionText(
                    text = "Longitud: ${city.coordenates.second}",
                    fontSize = 21.sp, //TODO: Agregar dimen a dimens.xml
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 5.dp) //TODO: Agregar dimen a dimens.xml
                )
                RegularText(
                    text = stringResource(id = R.string.city_detail),
                    fontSize = 12.sp, //TODO: Agregar dimen a dimens.xml
                    modifier = Modifier.padding(top = 10.dp) //TODO: Agregar dimen a dimens.xml
                ) // TODO(Pasar este texto como parametro)
            }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardPreview() {
    CitiesAppTheme {
        Column {
            CityCard(
                mockCities.first()
            ) {_, _ ->}
        }
    }
}