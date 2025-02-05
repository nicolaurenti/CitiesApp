package com.citiesapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.citiesapp.ui.theme.Dimen10dp
import com.citiesapp.ui.theme.Dimen15dp
import com.domain.model.CityModel

@Composable
fun PaginatedCityList(
    cities: List<CityModel>,
    loadMoreCities: () -> Unit,
    isLoading: Boolean,
    onCityClick: (CityModel) -> Unit,
    onFavoriteClick: (Int) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentPadding = PaddingValues(Dimen15dp)
    ) {
        itemsIndexed(cities) { _, city ->
            CityItem(
                city = city,
                modifier = Modifier
                    .clickable { onCityClick(city) }
                    .padding(bottom = Dimen10dp),
                onFavoriteClick = { onFavoriteClick(city.id) }
            )
        }

        if (isLoading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }

    if (cities.size > 10) {
        LaunchedEffect(listState) {
            snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                .collect { lastIndex ->
                    if (lastIndex != null && lastIndex >= cities.size - 5) {
                        loadMoreCities()
                    }
                }
        }
    }
}
