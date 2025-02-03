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
import com.citiesapp.ui.theme.CustomBlue
import com.citiesapp.ui.theme.CustomRed
import com.citiesapp.ui.theme.Dimen10dp
import com.citiesapp.ui.theme.Dimen15dp
import com.citiesapp.ui.theme.Dimen50dp
import com.citiesapp.ui.theme.Dimen5dp
import com.citiesapp.ui.theme.TextSizeDimen12dp
import com.citiesapp.ui.theme.TextSizeDimen20dp
import com.citiesapp.ui.theme.TextSizeDimen25dp
import com.citiesapp.ui.theme.mockCities
import com.domain.model.CityModel

@Composable
fun CityCard(
    city: CityModel,
    modifier: Modifier = Modifier,
    onFavoriteClick: (Int) -> Unit
) {
    Card(
        modifier = modifier.padding(Dimen15dp),
        elevation = CardDefaults.cardElevation(Dimen10dp),
        shape = RoundedCornerShape(Dimen15dp),
    ) {
        val favorite by remember { mutableStateOf(city.isFavorite) }
            Column(
                modifier = Modifier.padding(Dimen15dp)
            ) {
                Row {
                    TitleText(
                        text = stringResource(id = R.string.formated_city_data, city.name, city.country),
                        fontSize = TextSizeDimen25dp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = {
                        onFavoriteClick(city.id)
                    }) {
                        Icon(
                            imageVector = if (favorite) Icons.Filled.Star else Icons.Outlined.Star,
                            contentDescription = stringResource(id = R.string.favorites_content_description),
                            tint = if (favorite) CustomRed else Color.Gray,
                            modifier = Modifier.size(Dimen50dp)
                        )
                    }
                }
                DescriptionText(
                    text = stringResource(id = R.string.latitude_text, city.coordenates.first),
                    fontSize = TextSizeDimen20dp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = Dimen10dp)
                )
                DescriptionText(
                    text = stringResource(id = R.string.longitude_text, city.coordenates.second),
                    fontSize = TextSizeDimen20dp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = Dimen5dp)
                )
                RegularText(
                    text = stringResource(id = R.string.city_detail),
                    fontSize = TextSizeDimen12dp,
                    modifier = Modifier.padding(top = Dimen10dp)
                )
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
            ) {_ ->}
        }
    }
}