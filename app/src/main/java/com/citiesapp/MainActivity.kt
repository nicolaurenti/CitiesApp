package com.citiesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.citiesapp.ui.navigation.AppNavigation
import com.citiesapp.ui.theme.CitiesAppTheme
import com.citiesapp.viewmodel.CitiesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CitiesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCities()
        enableEdgeToEdge()
        setContent {
            CitiesAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize().background(Color.White)) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.surface,
                    ) {
                        val cities = viewModel.filteredCities.collectAsState().value
                        AppNavigation(
                            cities = cities,
                            onFavoriteClick = viewModel::onFavoriteClick,
                            onShowFavoritesClicked = viewModel::onShowingFavorites,
                            searchValue = viewModel::getSearchedCities,
                            loadMoreCities = viewModel::loadMoreCities,
                            isLoading = viewModel.isLoading.collectAsState().value
                        )
                    }
                }
            }
        }
    }
}