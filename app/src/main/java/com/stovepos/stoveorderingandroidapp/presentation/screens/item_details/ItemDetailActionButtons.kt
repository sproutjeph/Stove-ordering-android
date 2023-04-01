package com.stovepos.stoveorderingandroidapp.presentation.screens.item_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    ) {

        Button(
            onClick = { decreaseQty() },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            ),
            modifier = Modifier
                .padding(start = 16.dp),
            contentPadding = PaddingValues(2.dp)

        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = null,
            )
        }

        Text(text = "$itemQty",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )

        Button(
            onClick = { increaseQty() },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
           ),
            modifier = Modifier,
            contentPadding = PaddingValues(2.dp)

        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
            )
        }
    }

}


@Composable
fun AddToCartButton(
    itemPrice: Double
) {

    Button(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(end = 16.dp),
        onClick = { /*TODO*/ }
    ) {

        Icon(
            imageVector = Icons.Outlined.AddShoppingCart,
            contentDescription = null,
            modifier = Modifier
        )
        Spacer(modifier =   Modifier.width(6.dp))
        Text(text = "\$ $itemPrice",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimary,
        )

    }

}



