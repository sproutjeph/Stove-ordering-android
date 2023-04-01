package com.stovepos.stoveorderingandroidapp.presentation.screens.item_details

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ItemDetailsBottomAppBar(
    itemPrice: Double?,
    itemQty: Int,
    increaseQty: () -> Unit,
    decreaseQty: () -> Unit
) {


    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier
    ){


            QtyButtons(
                increaseQty = { increaseQty() },
                decreaseQty = { decreaseQty() },
                itemQty = itemQty
            )
        Spacer(modifier =   Modifier.weight(0.5f))
            AddToCartButton(
                itemPrice = itemPrice ?: 0.0
            )

    }
}