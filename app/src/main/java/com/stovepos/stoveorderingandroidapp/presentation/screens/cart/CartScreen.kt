package com.stovepos.stoveorderingandroidapp.presentation.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stovepos.stoveorderingandroidapp.R
import com.stovepos.stoveorderingandroidapp.presentation.components.StoveTopAppBar

@Composable
fun CartScreen(onBackButtonClicked: () -> Unit) {


    Scaffold(
        topBar = {
            StoveTopAppBar(
                title = R.string.cart_screen_title,
                isMainScreen = false,
                hasActionIcon = true,
                actionIcon = Icons.Default.MoreVert,
                onBackButtonClicked = onBackButtonClicked
            )
        },
        bottomBar = {},

        floatingActionButton = { 
            FloatingActionButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = Shapes().small
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(text = "CHECK OUT")
                    Text(text = "$200.0")

                }
            }
                               },
        floatingActionButtonPosition = FabPosition.Center


    ) {paddingValues->

       CartScreenContent(paddingValues = paddingValues)

    }

}


