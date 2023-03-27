package com.stovepos.stoveorderingandroidapp.presentation.screens.item_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun QtyButtons(
    itemQty: Int = 1,
    increaseQty: () -> Unit,
    decreaseQty: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {

        OutlinedButton(
            onClick = { decreaseQty() },
            shape = RoundedCornerShape(4.dp)
        ) {
            Icon(imageVector = Icons.Default.Remove, contentDescription = null)
        }

        Text(text = "$itemQty",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        OutlinedButton(
            onClick = { increaseQty() },
            shape = RoundedCornerShape(4.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }

}


@Composable
fun AddToCartButton(itemPrice: Double ) {

    Button(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),

        onClick = { /*TODO*/ }
    ) {

        Icon(
            imageVector = Icons.Outlined.AddShoppingCart,
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
        )

        Text(text = "Add to cart",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .weight(1f)
        )

        Text(text = "\$ $itemPrice",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .weight(1f)
        )

    }

}



