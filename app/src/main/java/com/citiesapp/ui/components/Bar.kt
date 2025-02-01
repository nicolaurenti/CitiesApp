package com.citiesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.citiesapp.R
import com.citiesapp.ui.theme.CitiesAppTheme
import com.citiesapp.ui.theme.Dimen10dp
import com.citiesapp.ui.theme.Dimen15dp
import com.citiesapp.ui.theme.Dimen2dp
import com.citiesapp.ui.theme.Dimen55dp
import com.citiesapp.ui.theme.TextSizeDimen14dp
import com.citiesapp.ui.theme.mockCities
import com.domain.model.CityModel

@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
    backEnabled: Boolean = true,
    onBackPressed: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimen15dp)
            .height(Dimen55dp)
            .background(Color.White)
    ) {
        if (backEnabled) {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_content_description),
                )
            }
        }
        TitleText(
            text = title,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimen15dp),
            textAlign = TextAlign.Start
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarCustom(searchValue: (String) -> Unit, modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    SearchBar(
        query = searchQuery,
        onQueryChange = { searchValue(it) },
        onSearch = { active = false },
        active = active,
        onActiveChange = { active = it },
        placeholder = { Text("Search") },
        modifier = modifier
            .padding(start = Dimen10dp, top = Dimen2dp, end = Dimen10dp, bottom = Dimen10dp)
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        },

        trailingIcon = {
            if (active)
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = null
                )
        },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        )
    ) {}
}

@Preview(showBackground = true)
@Composable
private fun BarPreview() {
    CitiesAppTheme {
        Column {
            TopBar(title = "Ciudades", backEnabled = false)
            TopBar(title = "Ciudades")
            SearchBarCustom({_:String -> })
        }
    }
}