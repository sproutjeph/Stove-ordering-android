package com.stovepos.stoveorderingandroidapp.presentation.screens.restaurents

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun RestaurantsScreen(
    onBackPressed: () -> Unit,
    onRestaurantClick: () -> Unit

    ) {

    Scaffold(
        topBar = { RestaurantTopAppBar(onBackPressed = onBackPressed) },
    ){paddingValues->
        RestaurantScreenContent(
            paddingValues = paddingValues,
            onRestaurantClick = onRestaurantClick
        )

    }


}