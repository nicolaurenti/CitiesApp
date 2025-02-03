package com.citiesapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.citiesapp.R
import com.citiesapp.ui.theme.CitiesAppTheme
import com.citiesapp.ui.theme.CustomBlue
import com.citiesapp.ui.theme.CustomRed
import com.citiesapp.ui.theme.Dimen10dp
import com.citiesapp.ui.theme.Dimen15dp
import com.citiesapp.ui.theme.Dimen2dp
import com.citiesapp.ui.theme.Dimen55dp
import com.citiesapp.ui.theme.Dimen5dp
import com.citiesapp.ui.theme.TextSizeDimen20dp
import kotlinx.coroutines.launch

@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
    backEnabled: Boolean = true,
    onBackPressed: () -> Unit = {},
    onShowFavoritesClicked: (Boolean) -> Unit = {},
) {
    var showingFavorite by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(bottom = 2.dp)
            .background(Color.White)
            .border(
                BorderStroke(2.dp, Color.Black),
                RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp, topEnd = 25.dp)
            )
    )
    {
        if (backEnabled) {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = CustomRed,
                    contentDescription = stringResource(id = R.string.back_content_description),
                )
            }
        }
        TitleText(
            text = title,
            fontSize = TextSizeDimen20dp,
            color = CustomBlue,
            modifier = Modifier
                .weight(1f)
                .padding(start = Dimen15dp),
            textAlign = TextAlign.Start
        )
        if (!backEnabled) {
            IconButton(onClick = {
                showingFavorite = !showingFavorite
                onShowFavoritesClicked(showingFavorite)
            }) {
                Icon(
                    imageVector = if (showingFavorite) Filled.Star else Outlined.Star,
                    contentDescription = stringResource(id = R.string.favorites_content_description),
                    tint = if (showingFavorite) CustomRed else Color.Gray
                )
            }
        }
    }
}

@Composable
fun SearchBarCustom(searchValue: (String) -> Unit) {
    var query by remember { mutableStateOf("") }
    var isFilterSelected by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier.fillMaxWidth().padding(Dimen5dp).background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            modifier =
            Modifier
                .weight(5f)
                .padding(end = 8.dp)
                .border(
                    width = 2.dp,
                    color = CustomBlue,
                    shape = RoundedCornerShape(20.dp)
                )
                .onFocusChanged {
                    isFilterSelected = !isFilterSelected
                },
            value = query,
            onValueChange = { newText ->
                query = newText
                coroutineScope.launch {
                    searchValue(query)
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "search Icon",
                    tint = CustomRed
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "clear text",
                    tint = CustomRed,
                    modifier = Modifier
                        .clickable {
                            coroutineScope.launch {
                                query = ""
                                searchValue(query)
                                isFilterSelected = !isFilterSelected
                            }
                        },
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = CustomBlue,
                unfocusedIndicatorColor = Color.White,
                disabledIndicatorColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                cursorColor = CustomRed,
                focusedTextColor = CustomRed,
                unfocusedTextColor = Color.Gray
            ),

            )
    }
}

@Preview(showBackground = true)
@Composable
private fun BarPreview() {
    CitiesAppTheme(darkTheme = true) {
        Column {
            TopBar(title = "Ciudades", backEnabled = false) {}
            TopBar(title = "Ciudades") {}
            SearchBarCustom({ _: String -> })
        }
    }
}