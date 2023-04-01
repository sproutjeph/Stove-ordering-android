package com.stovepos.stoveorderingandroidapp.presentation.screens.restaurents

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun RestaurantTopAppBar(
    onBackPressed: () ->  Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "All Restaurants")
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .clickable(onClick = onBackPressed)
            )
        },
        actions = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }
    )
}