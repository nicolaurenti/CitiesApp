package com.citiesapp.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.citiesapp.R
import com.domain.model.CityModel

val Poppins = FontFamily(
    Font(R.font.poppins, FontWeight.Normal),
    Font(R.font.poppins_bold, FontWeight.Bold)
)
val mockCities = listOf(
    CityModel("AR", "Tandil", 123456, Pair(-37.32, -59.14), false),
    CityModel("AR", "Buenos Aires", 987654, Pair(-34.61, -58.38), true),
    CityModel("US", "New York", 567890, Pair(40.71, -74.01), false),
    CityModel("FR", "Paris", 345678, Pair(48.85, 2.35), true),
    CityModel("ES", "Madrid", 234567, Pair(40.42, -3.70), false),
    CityModel("BR", "Rio de Janeiro", 765432, Pair(-22.91, -43.17), true),
    CityModel("JP", "Tokyo", 876543, Pair(35.68, 139.69), false),
    CityModel("IT", "Rome", 654321, Pair(41.90, 12.49), true),
    CityModel("DE", "Berlin", 543210, Pair(52.52, 13.40), false),
    CityModel("MX", "Mexico City", 432109, Pair(19.43, -99.13), true)
)