package com.citiesapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.citiesapp.ui.theme.CitiesAppTheme
import com.citiesapp.ui.theme.Poppins

@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        color = color,
        textAlign = textAlign,
        fontFamily = Poppins,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun DescriptionText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    color: Color = Color.Gray,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = FontWeight.Normal,
        color = color,
        textAlign = textAlign,
        fontFamily = Poppins,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun RegularText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = FontWeight.Normal,
        color = color,
        textAlign = textAlign,
        fontFamily = Poppins,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun TextPreview() {
    CitiesAppTheme {
        Column {
            TitleText(text = "Title")
            DescriptionText(text = "Description")
            RegularText(text = "Regular")
        }
    }
}