package com.stovepos.stoveorderingandroidapp.presentation.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stovepos.stoveorderingandroidapp.R
import com.stovepos.stoveorderingandroidapp.presentation.components.StoveTopAppBar
import org.mongodb.kbson.ObjectId

@Composable
fun CartScreen(
    onBackButtonClicked: () -> Unit,
    navigateToCartItemDetails: (String) -> Unit,
    onDeleteItem:(id: ObjectId) -> Unit

) {

    val cartScreenViewModel: CartScreenViewModel = viewModel()

    val cartItems by cartScreenViewModel.cartItems

    val totalItems = cartScreenViewModel.numberOfItemInCart
    val totalPrice = cartScreenViewModel.totalPrice





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
                    Text(text = "$$totalPrice")

                }
            }
                               },
        floatingActionButtonPosition = FabPosition.Center


    ) {paddingValues->

       CartScreenContent(
           paddingValues = paddingValues,
           allCartItems = cartItems,
           navigateToCartItemDetails = navigateToCartItemDetails,
           onDeleteItem = onDeleteItem,
           totalItemsInCart = totalItems,
           totalPrice = totalPrice
       )

    }

}


